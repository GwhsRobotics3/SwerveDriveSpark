/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberMoveArms extends Command {

  public ClimberMoveArms() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  private double deadband(double input) {
		if (Math.abs(input) < 0.15) return 0;
		return input;
	}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed1 = deadband(Robot.getOI().getClimberController().getLeftYValue());
    double speed2 = deadband(Robot.getOI().getClimberController().getRightYValue());
    Robot.m_climber.moveArms(speed1, speed2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
