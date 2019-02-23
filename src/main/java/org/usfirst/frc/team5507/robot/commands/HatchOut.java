/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class HatchOut extends Command {
  
  public Timer timer = new Timer();
  public HatchOut() {
    requires(Robot.m_HatchDelivery);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.m_HatchDelivery.placeHatch();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    
  }

  @Override
  protected void interrupted() {
  }
}
