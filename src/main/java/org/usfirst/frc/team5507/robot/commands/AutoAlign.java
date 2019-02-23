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
    Robot.m_Limelight.align(Robot.targetPos);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    drivetrain.setIsAuto(false);
    drivetrain.setFieldOriented(true);
  }

  @Override
  protected void interrupted() {
  }
}