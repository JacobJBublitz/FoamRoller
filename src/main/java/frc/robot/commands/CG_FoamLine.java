package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Constants;
import frc.robot.Robot;

public class CG_FoamLine extends CommandGroup {
    private static int runsLeft = -1;

    public CG_FoamLine() {
        CommandGroup group = new CommandGroup();
        group.addSequential(new CG_Cleanup());
        group.addSequential(new InstantCommand(() -> {
            if (runsLeft < 0) {
                runsLeft = Constants.CYCLES_AFTER_NO_MATERIAL;
            } else {
                runsLeft--;

                if (runsLeft == 0) {
                    Robot.getFailureLight().setFailure(true);
                    Scheduler.getInstance().removeAll();
                }
            }
            System.err.printf("Ran out of material! Running %d more time(s)!%n", runsLeft);
        }));
        addSequential(new ConditionalCommand(new InstantCommand(() -> runsLeft = -1), group) {
            @Override
            protected boolean condition() {
                return Robot.getRoller().hasMaterial();
            }
        });

        addParallel(new CG_PressSequence());
        addSequential(new CG_OvenSequence());
        addSequential(new C_AdvanceRoller(Constants.ROLLER_ADVANCE_SPEED, Constants.ROLLER_ADVANCE_DISTANCE));

        CommandGroup ovenPaperRipGroup = new CommandGroup();
        ovenPaperRipGroup.addSequential(new CG_Cleanup());
        ovenPaperRipGroup.addSequential(new InstantCommand(() -> {
            System.err.println("Detected a rip in the oven paper");
            Robot.getFailureLight().setFailure(true);
            Scheduler.getInstance().removeAll();
        }));
        addSequential(new ConditionalCommand(ovenPaperRipGroup) {
            @Override
            protected boolean condition() {
                return !Robot.getOven().isDetectingPaper();
            }
        });
        CommandGroup missedCutoutsGroup = new CommandGroup();
        missedCutoutsGroup.addSequential(new CG_Cleanup());
        missedCutoutsGroup.addSequential(new InstantCommand(() -> {
            System.err.println("Missed too many cutouts");
            Robot.getFailureLight().setFailure(true);
            Scheduler.getInstance().removeAll();
        }));
        addSequential(new ConditionalCommand(missedCutoutsGroup) {
            @Override
            protected boolean condition() {
                return Robot.getPress().missedToManyCutout();
            }
        });
    }
}
