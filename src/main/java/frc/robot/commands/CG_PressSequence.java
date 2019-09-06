package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CG_PressSequence extends CommandGroup {
    public CG_PressSequence() {
        addSequential(new C_ClosePress());
        addSequential(new WaitCommand(10));
        addSequential(new C_OpenPress());
        addSequential(new WaitCommand(2));
    }
}
