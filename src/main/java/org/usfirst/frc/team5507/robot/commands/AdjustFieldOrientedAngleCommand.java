package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class AdjustFieldOrientedAngleCommand extends Command {
	public static final double ADJUSTMENT_AMOUNT = 5;

	private final HolonomicDrivetrain mDrivetrain;
	private final boolean mIncreaseAngle;

	public AdjustFieldOrientedAngleCommand(HolonomicDrivetrain drivetrain, boolean increaseAngle) {
		mDrivetrain = drivetrain;
		mIncreaseAngle = increaseAngle;
	}

	@Override
	protected void execute() {
		if (mIncreaseAngle) {
			mDrivetrain.setAdjustmentAngle(mDrivetrain.getAdjustmentAngle() + ADJUSTMENT_AMOUNT);
		} else {
			mDrivetrain.setAdjustmentAngle(mDrivetrain.getAdjustmentAngle() - ADJUSTMENT_AMOUNT);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
