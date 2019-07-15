/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_FoamLine extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CG_FoamLine() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    addSequential(new C_WaitSec(2));

    addSequential(new C_Oven(true));
    addSequential(new C_WaitSec(5));
    addSequential(new C_Oven(false));
    addSequential(new C_WaitSec(2));

    addSequential(new C_SetRoller(.1));
    addSequential(new C_WaitSec(1));
    addSequential(new C_SetRoller(0));

    addSequential(new C_Press(true));
    
    addSequential(new C_WaitSec(5));
    addSequential(new C_Press(false));

    addSequential(new C_SetRoller(.1));
    addSequential(new C_WaitSec(1));
    addSequential(new C_SetRoller(0));


    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
