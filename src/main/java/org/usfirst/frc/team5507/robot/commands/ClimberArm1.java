/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberArm1 extends Command {
  public ClimberArm1() {

    requires(Robot.m_climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
   
    if(Math.abs(Robot.getOI().getClimberController().getLeftYValue()) > .15)
    {
     Robot.m_climber.moveArm1(Robot.getOI().getClimberController().getLeftYValue());
    }
    else 
    {
      Robot.m_climber.stopArm1();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_climber.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
