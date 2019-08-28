package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_FeedSequence extends CommandGroup {
    public CG_FeedSequence(double distance) {
        this(distance, true);
    }

    public CG_FeedSequence(double distance, boolean shouldCoolFoam) {
        if (shouldCoolFoam) {
            addSequential(new C_ActivateCooler());
        }

        addSequential(new C_SetRoller(0.1));
        addSequential(new C_WaitForRollerDistance(distance));
        addSequential(new C_SetRoller(0.0));

        if (shouldCoolFoam) {
            addSequential(new C_DeactivateCooler());
        }
    }
}
