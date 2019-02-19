package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

public class HolonomicDriveCommand extends Command {
	private final HolonomicDrivetrain mDrivetrain;

	public HolonomicDriveCommand(HolonomicDrivetrain drivetrain) {
		mDrivetrain = drivetrain;

		requires(drivetrain);
	}

	private double deadband(double input) {
		if ((!mDrivetrain.isFieldOriented())) {
			if(Math.abs(input) < 0.1) {
				return 0;
			}
			else {
				return input;
			}
		}
		if (Math.abs(input) < 0.25) return 0;
		return input;
	}

	@Override
	protected void execute() {
		
		if(mDrivetrain.getIsAuto())
		{
			mDrivetrain.setFieldOriented(false);
		}

		double forward = Robot.getOI().getController().getLeftYValue();
		double rotation = Robot.getOI().getController().getRightTriggerValue() - Robot.getOI().getController().getLeftTriggerValue();
		double strafe =  Robot.getOI().getController().getLeftXValue(); 
		///double arm1 = Robot.getOI().getClimberController().getLeftYValue();
		//double arm2 = Robot.getOI().getClimberController().getRightYValue();



		forward = deadband(forward);
		strafe = deadband(strafe);
		rotation = deadband(rotation);
		//arm1 = deadband(arm1);
		//arm2 = deadband(arm2);

		SmartDashboard.putNumber("Forward", forward);
		SmartDashboard.putNumber("Strafe", strafe);
		SmartDashboard.putNumber("Rotation", rotation);

		mDrivetrain.holonomicDrive(forward, strafe, rotation);
		//Robot.m_climber.moveArm1(arm1);
		//Robot.m_climber.moveArm2(arm2);
	}

	@Override
	protected void end() {
		mDrivetrain.stopDriveMotors();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
