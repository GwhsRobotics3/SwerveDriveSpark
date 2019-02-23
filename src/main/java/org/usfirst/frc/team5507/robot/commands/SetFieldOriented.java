/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.HolonomicDrivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetFieldOriented extends Command {
  private final boolean isFieldOriented;
  private final HolonomicDrivetrain drivetrain;

  public SetFieldOriented(HolonomicDrivetrain drivetrain) {
    this(drivetrain,true);
  }

  public SetFieldOriented(HolonomicDrivetrain drivetrain, boolean isFieldOriented)
  {
    this.drivetrain = drivetrain;
    this.isFieldOriented = isFieldOriented;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    drivetrain.setFieldOriented(isFieldOriented);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
