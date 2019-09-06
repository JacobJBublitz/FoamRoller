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

        addSequential(new C_AdvanceRoller(0.5, distance));

        if (shouldCoolFoam) {
            addSequential(new C_DeactivateCooler());
        }
    }
}
