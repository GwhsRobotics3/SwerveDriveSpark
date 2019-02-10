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
  private double kP = .0657;
  private double kI = .00138; //0/001387
  private double kD = 0.001;
  private double rI = 0.005;
  private double xErr = 0;
  private double xIErr = 0;
  private double rIErr = 0;
  private boolean happy = false;
  private double xOffset = 1;
  private ArrayList<Double> prevX = new ArrayList<Double>(); 
  public static final double WIDTH = 320;
  public static final double HEIGHT = 240;

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

  public void align(double targetPos) //check the values on limelight
  {
    Robot.swerveDriveSubsystem.setIsAuto(true); 
    limelightx = tx.getDouble(0.3);
    limelightx += xOffset;
    limelighty = ty.getDouble(0.3);
    limelightarea = ta.getDouble(0.3);
    isView = tv.getBoolean(true);
    angleErr = targetPos - (Robot.swerveDriveSubsystem.getGyroAngle() % 360);
    double rotation = 0;
    double strafe = 0;
    double forward = 0;
    prevX.add(angleErr);
    double dx = 0;
    if(prevX.size() > 2) dx = (prevX.get(prevX.size()-1) - prevX.get(prevX.size()-2))/.02;
    System.out.println("DX : " + dx);

    xErr = 0 - limelightx;
    xIErr += (xErr)*.02;
    if(Math.abs(angleErr) < 4) {
      rIErr += angleErr * .02;
    }
    else {
      if(angleErr < 0) {
        rIErr += -4*.02;
      }
      else {
        rIErr += 4*.02;
      }
    }
    
    if (Math.abs(limelightx) > .5) {
      strafe = kP*xErr + kI*xIErr;
      
    }
    if(Math.abs(angleErr) > 1.5 && Math.abs(limelightx) < 15) {
      rotation = (.0025 * angleErr) + (dx * kD) + rIErr*rI;
      System.out.println(angleErr);
      forward = 0.35;
    }
    forward = 0.35; // goes forward until area > 20
    if(limelightarea > 20) {
      forward = 0;  
      if(Math.abs(angleErr) < 1.5 && Math.abs(limelightx) < 1.5) {
        happy = true;
      }
    }
    if(happy) {
      strafe = 0;
      forward = 0.35;
    }
    if(isView) Robot.swerveDriveSubsystem.holonomicDrive(forward, -strafe, rotation); //forward: .3 * (1/limelightarea)
    else Robot.swerveDriveSubsystem.holonomicDrive(forward, -strafe, 0);
    
  }
}
