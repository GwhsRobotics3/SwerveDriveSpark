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
    requires(Robot.swerveDriveSubsystem);  
    requires(Robot.m_Limelight);
    this.drivetrain = drivetrain;
  }

  @Override
  protected void initialize() {
     }

  @Override
  protected void execute() {
    drivetrain.setIsAuto(true);
    drivetrain.setFieldOriented(false);
    if(Robot.m_Limelight.getCameraMode() == 0) {
      Robot.m_Limelight.align(Robot.targetPos, false);
      System.out.println("hatch: " + Robot.targetPos);
    }
    else {
      Robot.m_Limelight.align(Robot.targetPos + 180, true);
      double x = Robot.targetPos + 180;
      System.out.println("cargo: " + x);
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    drivetrain.setIsAuto(false);
    drivetrain.setFieldOriented(true);
    //Robot.swerveDriveSubsystem.stopDriveMotors();
  }

  @Override
  protected void interrupted() {
  }
}