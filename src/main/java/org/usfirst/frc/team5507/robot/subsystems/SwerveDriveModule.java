package org.usfirst.frc.team5507.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5507.robot.commands.SwerveModuleCommand;
 

public class SwerveDriveModule extends Subsystem {
	private static final long STALL_TIMEOUT = 2000;

	private long mStallTimeBegin = Long.MAX_VALUE;

	private double mLastError = 0, mLastTargetAngle = 0;

	private final int mModuleNumber;

	private final double mZeroOffset;

    private final CANEncoder mDriveEncoder;
	private final TalonSRX mAngleMotor;
    private final CANSparkMax mDriveMotor;
    
    private double zeroPos;

	public SwerveDriveModule(int moduleNumber, TalonSRX angleMotor, CANSparkMax driveMotor, double zeroOffset) {
		mModuleNumber = moduleNumber;

		mAngleMotor = angleMotor;
        mDriveMotor = driveMotor;
        mDriveEncoder = mDriveMotor.getEncoder();

        mZeroOffset = zeroOffset;
        zeroPos = mDriveEncoder.getPosition();

           angleMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
            angleMotor.setSensorPhase(true);
	        angleMotor.config_kP(0, 20.2, 0);
	        angleMotor.config_kI(0, 0.001, 0);
	        angleMotor.config_kD(0, 60, 0);
	        angleMotor.setNeutralMode(NeutralMode.Brake);
            angleMotor.set(ControlMode.Position, 0);
            angleMotor.configNeutralDeadband(0.07);
           // driveMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); ADD TO CANSPARKMAX LATER***
           

            driveMotor.setIdleMode(IdleMode.kBrake);


	        // Set amperage limits
	        angleMotor.configContinuousCurrentLimit(30, 0);
	        angleMotor.configPeakCurrentLimit(30, 0);
	        angleMotor.configPeakCurrentDuration(100, 0);
	        angleMotor.enableCurrentLimit(true);
            
            driveMotor.setSmartCurrentLimit(15);
            driveMotor.setSecondaryCurrentLimit(15,0);
            driveMotor.setCANTimeout(0);
            //driveMotor.enableCurrentLimit(true); try using pheonix tuner
	     
	}
	
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SwerveModuleCommand(this));
    }

    public TalonSRX getAngleMotor() {
        return mAngleMotor;
    }

    /**
     * Get the current angle of the swerve module
     *
     * @return An angle in the range [0, 360)
     */
    public double getCurrentAngle() {
        double angle = mAngleMotor.getSelectedSensorPosition(0) * (360.0 / 1024.0);
        angle -= mZeroOffset;
        angle %= 360;
        if (angle < 0) angle += 360;

        return angle;
    }

  
    public CANSparkMax getDriveMotor() {
        return mDriveMotor;
    }

    public void robotDisabledInit() {
        mStallTimeBegin = Long.MAX_VALUE;
    }

    public void setTargetAngle(double targetAngle) {
    	
        mLastTargetAngle = targetAngle;

        targetAngle %= 360;

        SmartDashboard.putNumber("Module Target Angle " + mModuleNumber, targetAngle % 360);

        targetAngle += mZeroOffset;

        double currentAngle = mAngleMotor.getSelectedSensorPosition(0) * (360.0 / 1024.0);
        double currentAngleMod = currentAngle % 360;
        if (currentAngleMod < 0) currentAngleMod += 360;

        double delta = currentAngleMod - targetAngle;

        if (delta > 180) {
            targetAngle += 360;
        } else if (delta < -180) {
            targetAngle -= 360;
        }

        delta = currentAngleMod - targetAngle;
        if (delta > 90 || delta < -90) {
            if (delta > 90)
                targetAngle += 180;
            else if (delta < -90)
                targetAngle -= 180;
            mDriveMotor.setInverted(false);
        } else {
            mDriveMotor.setInverted(true);
        }

        targetAngle += currentAngle - currentAngleMod;

        double currentError = mAngleMotor.getClosedLoopError(0);
//        if (Math.abs(currentError - mLastError) < 7.5 &&
//                Math.abs(currentAngle - targetAngle) > 5) {
//            if (mStallTimeBegin == Long.MAX_VALUE) {
//            	mStallTimeBegin = System.currentTimeMillis();
//            }
//            if (System.currentTimeMillis() - mStallTimeBegin > STALL_TIMEOUT) {
//            	angleMotorJam = true;
//            	mAngleMotor.set(ControlMode.Disabled, 0);
//            	mDriveMotor.set(ControlMode.Disabled, 0);
//            	SmartDashboard.putBoolean("Motor Jammed" + moduleNumber, angleMotorJam);
//            	return;
//            }
//        } else {
//            mStallTimeBegin = Long.MAX_VALUE;
//        }
        mLastError = currentError;
        targetAngle *= 1024.0 / 360.0;
        mAngleMotor.set(ControlMode.Position, targetAngle);
    }


    public void setTargetSpeed(double speed) {
        mDriveMotor.set(speed);
    }

    public void resetEncoder() {
        zeroPos = mDriveEncoder.getPosition(); 
    }

    public double getEncoderDiff() {
        return mDriveEncoder.getPosition() - zeroPos;
    }

    public double getTargetAngle() {
    	return mLastTargetAngle;
    }

    public double encoderTicksToInches(double ticks) {
         return ticks / 35.6;
    }

    public int inchesToEncoderTicks(double inches) {
         return (int) Math.round(inches * 35.6);
    }

    public double getInches() {
        return encoderTicksToInches(mDriveEncoder.getPosition());
    }

    public double getDriveDistance() { 
        double ticks = mDriveEncoder.getPosition();
        return encoderTicksToInches(ticks);
    }
}
