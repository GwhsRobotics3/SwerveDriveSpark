package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberStop extends Command {
  public ClimberStop() {
    requires(Robot.m_climber);
  } 

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_climber.stop();
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
