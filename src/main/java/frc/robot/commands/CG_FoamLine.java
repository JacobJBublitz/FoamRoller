package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class CG_FoamLine extends CommandGroup {
    public CG_FoamLine() {
        CommandGroup group = new CommandGroup();
        group.addSequential(new CG_Cleanup());
        group.addSequential(new Command() {
            @Override
            protected void initialize() {
                System.out.println("Ran out of material");
            }

            @Override
            protected boolean isFinished() {
                return false;
            }
        });
        addSequential(new ConditionalCommand(group) {
            @Override
            protected boolean condition() {
                return !Robot.getRoller().hasMaterial();
            }
        });

        addParallel(new CG_PressSequence());
        addSequential(new CG_OvenSequence());
        addSequential(new CG_FeedSequence(2.98));
    }
}
