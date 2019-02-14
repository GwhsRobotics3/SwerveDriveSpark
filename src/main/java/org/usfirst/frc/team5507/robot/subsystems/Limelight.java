/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.commands.ShowLimelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final double REFRESH_RATE = .02;
  private static int camMode = 0;
  public static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public static NetworkTableEntry tx = table.getEntry("tx");
  public static NetworkTableEntry ty = table.getEntry("ty");
  public static NetworkTableEntry ta = table.getEntry("ta");
  public static NetworkTableEntry tv = table.getEntry("tv");

  // read values periodically
  public static double limelightx;
  public static double limelighty;
  public static double limelightarea;
  public static double angleErr;
  public static boolean isView;
  private double dx = 0;
  private double kP = .0657;
  private double kI = .00138; 
  private double kD = 0.001;
  private double rI = 0.005;
  private double xErr = 0;
  private double xIErr = 0;
  private double rIErr = 0;
  private boolean happy = false;
  private double xOffset = 1;
  private double rotation = 0.0;
  private double strafe = 0;
  private double forward = 0.35;
  //private ArrayList<Double> prevX = new ArrayList<Double>(); 
  private Double currAngleErr; // starts as null
  private Double prevAngleErr; // starts as null

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShowLimelight());
  }

  public void resetHappy()
  {
    happy = false;
    xIErr = 0;
    rIErr = 0;
  }
  
  public  void switchModes() {
    if (camMode == 0) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      camMode = 1;
    } 
    else {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      camMode = 0;
    }
  }

  public void printInfo() {
    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", limelightx);
    SmartDashboard.putNumber("LimelightY", limelighty);
    SmartDashboard.putNumber("LimelightArea", limelightarea);
    SmartDashboard.putBoolean("Is in view", isView);
    SmartDashboard.putNumber("Gyro Angle", (Robot.swerveDriveSubsystem.getGyroAngle()));
  }

  public boolean getHappy()
  {
    return happy;
  }
  public double getStrafe() {
    xErr = xOffset - limelightx;
    xIErr += (xErr)*REFRESH_RATE;
    
    if (Math.abs(limelightx) > .5) {
      strafe = kP*xErr + kI*xIErr;
    }

    if(isHappy()) {
      strafe = 0;
      
    }
    System.out.println("STRAFE: " + strafe);
    return strafe;
  }

  public double getRotation() {
    if(Math.abs(angleErr) < 4) {
      rIErr += angleErr * REFRESH_RATE;
    }
    else {
      if(angleErr < 0) {
        rIErr += -4*REFRESH_RATE;
      }
      else {
        rIErr += 4*REFRESH_RATE;
      }
    }
    if(Math.abs(angleErr) > 1.5 && Math.abs(limelightx) < 15) {
      rotation = (.0035 * angleErr) + (dx * kD) + rIErr*rI;
      System.out.println(angleErr);
      forward = 0.35;
    }
    System.out.println("ROTATION: " + rotation);
    return rotation;
  }

  public boolean isHappy() {
    if(limelightarea > 20) {
      forward = 0;  
      if(Math.abs(angleErr) < 1.5 && Math.abs(limelightx) < 1.5) {
        happy = true;
      }
    }
    return happy;
  }

  public double getAngleErr(double targetAngle) {
    double angleErr = targetAngle - (Robot.swerveDriveSubsystem.getGyroAngle() % 360);
    return angleErr;
  }

  public double setDX(double targetAngle)
  {
    Double var = currAngleErr;
    currAngleErr = Robot.m_Limelight.getAngleErr(targetAngle);
    if(var != null)
    {
      prevAngleErr = var;
      return (currAngleErr - prevAngleErr) / REFRESH_RATE;
    }
    return 0;
  }

  public void align(double targetAngle) //check the values on limelight
  {
    angleErr = this.getAngleErr(targetAngle);
    Robot.swerveDriveSubsystem.setIsAuto(true); 
    limelightx = tx.getDouble(0.3);
    limelightx += xOffset;
    limelighty = ty.getDouble(0.3);
    limelightarea = ta.getDouble(0.3);
    isView = tv.getBoolean(true);
    dx = Robot.m_Limelight.setDX(targetAngle);
    forward = 0.35;
    
   
    

    if(isView) {
      Robot.swerveDriveSubsystem.holonomicDrive(forward, -getStrafe(), getRotation());
    }
    else {
      Robot.swerveDriveSubsystem.holonomicDrive(forward, -getStrafe(), 0);
    } 
  }
}
