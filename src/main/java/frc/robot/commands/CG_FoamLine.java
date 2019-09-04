package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CG_FoamLine extends CommandGroup {
    public CG_FoamLine() {
        addParallel(new CG_OvenSequence());
        addSequential(new CG_PressSequence());
        addSequential(new CG_FeedSequence(2.98));

        //addSequential(new CG_PressSequence());
        //addSequential(new CG_FeedSequence(2.0, false));
    }
}
