/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_Oven extends Command {
  private boolean state;
  public C_Oven(boolean state) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.state = state;
    requires(Robot.getRoller());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (state) {
      System.out.println("Closing Oven");
    } else {
      System.out.println("Opening Oven");
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!Robot.getRoller().getEndOfRoll()){
      Robot.getRoller().closeOven(state);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
