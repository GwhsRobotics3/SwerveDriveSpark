package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CargoOut extends Command {
  public CargoOut() {
    requires(Robot.m_cargo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.m_cargo.pushCargo();
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
