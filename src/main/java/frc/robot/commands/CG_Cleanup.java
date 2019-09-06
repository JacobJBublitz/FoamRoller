package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_Cleanup extends CommandGroup {
    public CG_Cleanup() {
        addSequential(new C_Oven(false));
        addSequential(new C_OpenPress());
        addSequential(new C_DeactivateCooler());
        addSequential(new C_SetRoller(0.0));
    }
}
