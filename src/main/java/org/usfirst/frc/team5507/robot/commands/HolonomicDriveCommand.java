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
		if (mDrivetrain.getIsAuto()) return input;
		if (Math.abs(input) < 0.15) return 0;
		return input;
	}

	@Override
	protected void execute() {
		double forward = Robot.getOI().getController().getLeftYValue();
		double rotation = Robot.getOI().getController().getRightTriggerValue() - Robot.getOI().getController().getLeftTriggerValue();
		double strafe =  Robot.getOI().getController().getLeftXValue(); 

		forward = deadband(forward);
		strafe = deadband(strafe);
		rotation = deadband(rotation);

		SmartDashboard.putNumber("Forward", forward);
		SmartDashboard.putNumber("Strafe", strafe);
		SmartDashboard.putNumber("Rotation", rotation);

		mDrivetrain.holonomicDrive(forward, strafe, rotation);
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
