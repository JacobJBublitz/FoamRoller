package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_DeactivateCooler extends InstantCommand {
    public C_DeactivateCooler() {
        super(() -> Robot.getCooler().deactivateCooler());
    }
}
