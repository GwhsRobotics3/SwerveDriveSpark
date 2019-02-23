package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberMoveArms extends Command {

  public ClimberMoveArms() {
    requires(Robot.m_climber);
  }

  @Override
  protected void initialize() {
  }

  private double deadband(double input) {
		if (Math.abs(input) < 0.15) return 0;
		  return input;
  }
  
  @Override
  protected void execute() {
    double speed1 = deadband(Robot.getOI().getClimberController().getLeftYValue());
    double speed2 = deadband(Robot.getOI().getClimberController().getRightYValue());
    Robot.m_climber.moveArms(speed1, speed2);
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
