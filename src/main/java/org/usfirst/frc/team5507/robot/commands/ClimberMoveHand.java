package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberMoveHand extends Command {
private double speed;

  public ClimberMoveHand(double speed) {
    requires(Robot.m_climber);
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_climber.moveHand1(speed);
  }

  @Override
  protected boolean isFinished() {
    return speed == 0;
  }

  @Override
  protected void end() {
  }


  @Override
  protected void interrupted() {
  }
}
