package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CG_OvenSequence extends CommandGroup {
    public CG_OvenSequence() {
        addSequential(new C_Oven(true));
        addSequential(new WaitCommand(115));
        addSequential(new C_Oven(false));
        addSequential(new WaitCommand(2));
    }
}
