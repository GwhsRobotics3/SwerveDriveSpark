/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateAngle extends Command {
  private double angle; 
  

  public RotateAngle(double a) {
    requires(Robot.swerveDriveSubsystem);
    angle = a;
  }

  @Override
  protected void initialize() {
  Robot.swerveDriveSubsystem.getNavX().zeroYaw();
  }

  @Override
  protected void execute() {
   double angleErr = angle - Robot.swerveDriveSubsystem.getNavX().getYaw();
   if(angleErr > 0) {
     Robot.swerveDriveSubsystem.holonomicDrive(0, 0, 0.4);
   }
   else if(angleErr < 0) {
     Robot.swerveDriveSubsystem.holonomicDrive(0, 0, -0.4);
   }
  }

  @Override
  protected boolean isFinished() {
    double angleErr = angle - Robot.swerveDriveSubsystem.getNavX().getYaw();
    if(Math.abs(angleErr) < 2) {
    return true;
    }

    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
