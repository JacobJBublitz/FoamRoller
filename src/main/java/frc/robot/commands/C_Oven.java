package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_Oven extends InstantCommand {
    public C_Oven(boolean state) {
        super(() -> {
            Robot.getOven().closeOven(state);
        });
    }
}
