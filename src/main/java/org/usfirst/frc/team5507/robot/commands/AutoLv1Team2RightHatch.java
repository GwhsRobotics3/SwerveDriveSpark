/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLv1Team2RightHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoLv1Team2RightHatch() {
    addSequential(new DriveForwardDistance(11, .5)); // for testing
    addSequential(new DriveSidewaysDistance(2, .5)); // for testing
    //addSequential(new AutoAlign(0));
    addSequential(new HatchOut());
    addSequential(new HatchIn());
  }
}
