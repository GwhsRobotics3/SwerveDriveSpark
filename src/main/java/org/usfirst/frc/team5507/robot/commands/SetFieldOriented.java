/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetFieldOriented extends Command {
  private final boolean isFieldOriented;
  private final HolonomicDrivetrain drivetrain;
  public SetFieldOriented(HolonomicDrivetrain drivetrain) {
    this(drivetrain,true);
  }

  public SetFieldOriented(HolonomicDrivetrain drivetrain, boolean isFieldOriented)
  {
    this.drivetrain = drivetrain;
    this.isFieldOriented = isFieldOriented;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.setFieldOriented(isFieldOriented);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
