package org.usfirst.frc.team5507.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class Drivetrain extends Subsystem {
	private double speedMultiplier = 1;

	protected abstract void initDefaultCommand();

	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
}
