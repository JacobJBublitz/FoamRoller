/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Stopwatch;

public class C_WaitSec extends Command {
  private Stopwatch stopwatch;
  private double waitTime;
  public C_WaitSec(double waitTime) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.waitTime = waitTime;
    stopwatch = new Stopwatch();
    
  }
  @Override
  protected void initialize() {
    stopwatch.reset();
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("wait time set: " + waitTime+ "   actual: " + stopwatch.getSeconds());
    return stopwatch.getSeconds() >= waitTime;
  }
}
