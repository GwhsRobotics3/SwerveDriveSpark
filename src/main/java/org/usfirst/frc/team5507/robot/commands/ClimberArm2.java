package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberArm2 extends Command {
  public ClimberArm2() {
    requires(Robot.m_climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Math.abs(Robot.getOI().getClimberController().getRightYValue()) > .15)
     Robot.m_climber.moveArm2(Robot.getOI().getClimberController().getRightYValue());
    else 
      Robot.m_climber.stopArm2();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.m_climber.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
