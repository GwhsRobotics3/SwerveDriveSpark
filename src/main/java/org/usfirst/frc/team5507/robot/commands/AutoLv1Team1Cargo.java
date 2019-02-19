/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLv1Team1Cargo extends CommandGroup {

  public AutoLv1Team1Cargo() {
    addSequential(new DriveForwardDistance(18, 0.5));
    addSequential(new RotateAngle(90));
    ////addSequential(new AutoAlign(90));
    addSequential(new CargoOut());
    addSequential(new CargoIn());
  }
}
