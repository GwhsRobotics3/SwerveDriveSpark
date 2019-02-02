/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team5507.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static CANSparkMax arm1 = new CANSparkMax(1, MotorType.kBrushless);
  private static CANSparkMax arm2 = new CANSparkMax(2, MotorType.kBrushless);
  private static CANEncoder NEncoder1 = new CANEncoder(arm1);
  private static CANEncoder NEncoder2 = new CANEncoder(arm2);
  private static CANPIDController NPidController1 = new CANPIDController(arm1);
  private static CANPIDController NPidController2 = new CANPIDController(arm2);

  private final double GEARBOX_RATIO = 400; 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ClimberStop());
  }
  
  public CANPIDController getPIDControllerArm1()
  {
    return NPidController1;
  }

  public CANPIDController getPIDControllerArm2()
  {
    return NPidController2;
  }

  public void stop() { //when pressed
    arm1.set(0);
    arm2.set(0);
  }

  public void latch() { //when held
    arm1.set(.5);
  }

  public void rollUp() { //when held
    arm1.set(.3);
    arm2.set(.3);
  }

  public void pullUpArm2() { //when held
    arm2.set(-0.3);
  }

  public void pullUpArm1() { //when held
    arm1.set(-0.3);
  } 
   
  public void armOneMove(double speed) {//up or down with joystick
    arm1.set(speed);
  }
    // kP = 0.2; 
    // kI = 1e-4;
    // kD = 1; 
    // kIz = 0; 
    // kFF = 0; 
    // kMaxOutput = 1; 
    // kMinOutput = -1;

  public static void setPID(CANPIDController Controller,double p, double i, double d) //used in RobotInit
  {
    Controller.setP(p);
    Controller.setI(i);
    Controller.setD(d);
  }


  public void armOneZero()
  {
    NPidController1.setReference(0, ControlType.kPosition);
  }

  public void armOneFortyFive()
  {
   NPidController1.setReference(50, ControlType.kPosition);
  }

  public void armToDegree(double angle) {
    NPidController1.setReference((angle/360) * GEARBOX_RATIO, ControlType.kPosition);
  }
}

