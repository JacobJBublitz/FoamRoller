package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;

public class CG_OvenSequence extends CommandGroup {
    public CG_OvenSequence() {
        addSequential(new C_Oven(true));
        addSequential(new WaitCommand(Constants.ACTUATION_WAIT));
        addSequential(new WaitCommand(Constants.OVEN_TIME));
        addSequential(new C_Oven(false));
        addSequential(new WaitCommand(Constants.ACTUATION_WAIT));
    }
}
