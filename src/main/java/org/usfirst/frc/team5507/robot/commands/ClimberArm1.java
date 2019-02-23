package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberArm1 extends Command {
  public ClimberArm1() {
    requires(Robot.m_climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
   
    if(Math.abs(Robot.getOI().getClimberController().getLeftYValue()) > .15)
     Robot.m_climber.moveArm1(Robot.getOI().getClimberController().getLeftYValue());
    else 
      Robot.m_climber.stopArm1();
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
