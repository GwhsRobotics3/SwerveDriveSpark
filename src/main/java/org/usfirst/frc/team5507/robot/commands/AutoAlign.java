/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class AutoAlign extends Command {
  private final HolonomicDrivetrain drivetrain;
  
  public AutoAlign(HolonomicDrivetrain drivetrain) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.swerveDriveSubsystem);  
    requires(Robot.m_Limelight);
    this.drivetrain = drivetrain;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
     }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.setIsAuto(true);
    drivetrain.setFieldOriented(false);
    Robot.m_Limelight.align(Robot.targetPos);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.setIsAuto(false);
    drivetrain.setFieldOriented(true);
    //Robot.swerveDriveSubsystem.stopDriveMotors();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}