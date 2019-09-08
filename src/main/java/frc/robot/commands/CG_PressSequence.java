package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Robot;

public class CG_PressSequence extends CommandGroup {
    public CG_PressSequence() {
        addSequential(new C_ClosePress());
        addSequential(new WaitCommand(Constants.ACTUATION_WAIT));
        addSequential(new WaitCommand(Constants.PRESS_TIME));
        addSequential(new C_OpenPress());
        addSequential(new InstantCommand(() -> Robot.getPress().startClear()));

        addSequential(new WaitCommand(Constants.OVEN_TIME - Constants.PRESS_TIME + Constants.ACTUATION_WAIT));
        addSequential(new InstantCommand(() -> Robot.getPress().endClear()));
    }
}
