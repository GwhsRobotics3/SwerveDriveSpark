/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLv2Team3Hatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoLv2Team3Hatch() {
   addSequential(new DriveForwardDistance(15, 0.5));
   addSequential(new RotateAngle(-90));
   //addSequential(new AutoAlign(-90));
   addSequential(new HatchOut());
   addSequential(new HatchIn());
  }
}
