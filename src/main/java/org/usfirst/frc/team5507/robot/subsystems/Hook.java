/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Hook extends Subsystem {
  private static CANSparkMax hook = new CANSparkMax(25, MotorType.kBrushless);
  private static CANEncoder hookEncoder = new CANEncoder(hook);
  private static CANPIDController hookPID = new CANPIDController(hook);


  @Override
  public void initDefaultCommand() {
    Robot.m_hook.setPid();
  }

  public void moveHook(double speed) {
    if(Math.abs(speed) < .1) 
    {
      speed = 0;
    }
    hook.set(speed);
  }

  public double getPos() {
    return hookEncoder.getPosition();
  }

  public void setPosition(double pos)
  {
    hookPID.setReference(pos, ControlType.kPosition);
  }

  public void setPid()
  {
    hookPID.setP(.05);
    hookPID.setI(.000001);
    hookPID.setFF(0); 
  }
}
