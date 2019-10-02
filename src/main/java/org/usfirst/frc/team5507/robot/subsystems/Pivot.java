/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Pivot extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
<<<<<<< HEAD
DoubleSolenoid pivot = new DoubleSolenoid(6, 7);
=======
  DoubleSolenoid pivotArm = new DoubleSolenoid(6, 7); 
>>>>>>> 84fb1502f8843511f828553e95f737059608a900

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

<<<<<<< HEAD
  public void PivotUp()
  {
    pivot.set(DoubleSolenoid.Value.kForward);
  }
  public void PivotDown()
  {
    pivot.set(DoubleSolenoid.Value.kReverse);
  }
=======
  public void high()
  {
    pivotArm.set(DoubleSolenoid.Value.kForward); // might be wrong way
  }

  public void low()
  {
    pivotArm.set(DoubleSolenoid.Value.kReverse); // might be wrong way
  }

  
>>>>>>> 84fb1502f8843511f828553e95f737059608a900
}
