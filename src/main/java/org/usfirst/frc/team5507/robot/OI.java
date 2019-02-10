package org.usfirst.frc.team5507.robot;

import org.usfirst.frc.team5507.robot.commands.AdjustFieldOrientedAngleCommand;
import org.usfirst.frc.team5507.robot.commands.AutoAlign;
import org.usfirst.frc.team5507.robot.commands.ClimberLatch;
import org.usfirst.frc.team5507.robot.commands.ClimberPullDownArm1;
import org.usfirst.frc.team5507.robot.commands.ClimberPullDownArm2;
import org.usfirst.frc.team5507.robot.commands.ClimberPullUpArm1;
import org.usfirst.frc.team5507.robot.commands.ClimberPullUpArm2;
import org.usfirst.frc.team5507.robot.commands.PlaceHatch;
import org.usfirst.frc.team5507.robot.commands.ResetDrivetrainEncoderCommand;
import org.usfirst.frc.team5507.robot.commands.RetractHatch;
import org.usfirst.frc.team5507.robot.commands.StopArm1;
import org.usfirst.frc.team5507.robot.commands.StopArm2;
import org.usfirst.frc.team5507.robot.commands.SwitchLedModes;
import org.usfirst.frc.team5507.robot.commands.ToggleFieldOrientedCommand;
import org.usfirst.frc.team5507.robot.commands.ZeroNavX;
import org.usfirst.frc.team5507.robot.input.DPadButton;
import org.usfirst.frc.team5507.robot.input.IGamepad;
import org.usfirst.frc.team5507.robot.input.XboxGamepad;
import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private IGamepad mController = new XboxGamepad(0);

	private Robot mRobot;

	public OI(Robot robot) {
		mRobot = robot;
	}

	public void registerControls() { 
		//mController.getAButton().whenPressed(new ResetDrivetrainEncoderCommand(mRobot.getDrivetrain()));
		// mController.getBButton().whenPressed(new PlaceHatch());
		// mController.getAButton().whenPressed(new RetractHatch());
		// mController.getXButton().whileHeld(new AutoAlign());
		// mController.getYButton().whenPressed(new ZeroNavX());
		//mController.getLeftBumperButton().whenPressed(new SwitchLedModes());
		mController.getAButton().whenPressed(new ClimberPullUpArm1());
		mController.getAButton().whenReleased(new StopArm1());

		mController.getXButton().whenPressed(new ClimberPullUpArm2());
		mController.getXButton().whenReleased(new StopArm2());

		mController.getBButton().whenPressed(new ClimberPullDownArm1());
		mController.getBButton().whenReleased(new StopArm1());

		mController.getYButton().whenPressed(new ClimberPullDownArm2());
		mController.getYButton().whenReleased(new StopArm2());
		
		mController.getStartButton().whenPressed(new ToggleFieldOrientedCommand(Robot.swerveDriveSubsystem));
		 //A and B arm 1
		 //X and Y arm 2

		//mController.getStartButton().whenPressed(new ToggleFieldOrientedCommand(mRobot.getDrivetrain()));
		//mController.getDPadButton(DPadButton.Direction.LEFT).whenPressed(new AdjustFieldOrientedAngleCommand(mRobot.getDrivetrain(), false));
		//mController.getDPadButton(DPadButton.Direction.RIGHT).whenPressed(new AdjustFieldOrientedAngleCommand(mRobot.getDrivetrain(), true));
		//mController.getXButton().whenPressed(new ClimberLatch());
	}

	public IGamepad getController() {
		return mController;
	}
}
