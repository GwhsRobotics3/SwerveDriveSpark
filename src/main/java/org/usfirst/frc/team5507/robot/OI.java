package org.usfirst.frc.team5507.robot;

import org.usfirst.frc.team5507.robot.commands.AdjustFieldOrientedAngleCommand;
import org.usfirst.frc.team5507.robot.commands.AdjustSpeedMultiplier;
import org.usfirst.frc.team5507.robot.commands.AutoAlign;
import org.usfirst.frc.team5507.robot.commands.CargoIn;
import org.usfirst.frc.team5507.robot.commands.CargoOut;
import org.usfirst.frc.team5507.robot.commands.ClimberArm1;
import org.usfirst.frc.team5507.robot.commands.ClimberArm2;
import org.usfirst.frc.team5507.robot.commands.ClimberMoveHand;
import org.usfirst.frc.team5507.robot.commands.ClimberStop;
import org.usfirst.frc.team5507.robot.commands.HatchIn;
import org.usfirst.frc.team5507.robot.commands.HatchOut;
import org.usfirst.frc.team5507.robot.commands.HatchPushBack;
import org.usfirst.frc.team5507.robot.commands.HookBack;
import org.usfirst.frc.team5507.robot.commands.HookForward;
import org.usfirst.frc.team5507.robot.commands.PivotHigh;
import org.usfirst.frc.team5507.robot.commands.PivotLow;
import org.usfirst.frc.team5507.robot.commands.ResetDrivetrainEncoderCommand;
import org.usfirst.frc.team5507.robot.commands.ResetHappy;
import org.usfirst.frc.team5507.robot.commands.SetFieldOriented;
import org.usfirst.frc.team5507.robot.commands.StopArm1;
import org.usfirst.frc.team5507.robot.commands.StopArm2;
import org.usfirst.frc.team5507.robot.commands.SwitchLedModes;
import org.usfirst.frc.team5507.robot.commands.ToggleCamera;
import org.usfirst.frc.team5507.robot.commands.ToggleFieldOrientedCommand;
import org.usfirst.frc.team5507.robot.commands.ZeroNavX;
import org.usfirst.frc.team5507.robot.input.DPadButton;
import org.usfirst.frc.team5507.robot.input.IGamepad;
import org.usfirst.frc.team5507.robot.input.XboxGamepad;
import org.usfirst.frc.team5507.robot.input.DPadButton.Direction;
import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private IGamepad mController = new XboxGamepad(0);
	private IGamepad climbController = new XboxGamepad(1);

	private Robot mRobot;

	public OI(Robot robot) {
		mRobot = robot;
	}

	public void registerControls() { 
		//main controls
		mController.getAButton().whileHeld(new CargoOut());
		mController.getAButton().whenReleased(new CargoIn());

		mController.getBButton().whileHeld(new HatchOut());
		mController.getBButton().whenReleased(new HatchIn());

		mController.getXButton().whileHeld(new AutoAlign(mRobot.getDrivetrain()));
		mController.getXButton().whenReleased(new ResetHappy());
		
		mController.getYButton().whenPressed(new ZeroNavX());


		mController.getRightBumperButton().whenPressed(new SwitchLedModes());
		mController.getLeftBumperButton().whenPressed(new ToggleCamera());

		mController.getStartButton().whenPressed(new AdjustSpeedMultiplier(.3));
		mController.getBackButton().whenPressed(new AdjustSpeedMultiplier(.825));

		// mController.getStartButton().whenPressed(new SetFieldOriented(mRobot.getDrivetrain(),true));
		// mController.getBackButton().whenPressed(new SetFieldOriented(mRobot.getDrivetrain(), false));

		
		mController.getDPadButton(Direction.RIGHT).whenPressed(new AdjustFieldOrientedAngleCommand(mRobot.getDrivetrain(), true));
		mController.getDPadButton(Direction.LEFT).whenPressed(new AdjustFieldOrientedAngleCommand(mRobot.getDrivetrain(), false));

		//climber controls
		
		climbController.getRightBumperButton().whileHeld(new ClimberMoveHand(.7));
		climbController.getRightBumperButton().whenReleased(new ClimberMoveHand(0));

		climbController.getYButton().whenPressed(new HookForward());
		climbController.getBButton().whenPressed(new HookBack());

		climbController.getAButton().whileHeld(new PivotLow());
		climbController.getAButton().whenReleased(new PivotHigh());
	}	

	public IGamepad getController() {
		return mController;
	}

	public IGamepad getClimberController() {
		return climbController;
	}
}
