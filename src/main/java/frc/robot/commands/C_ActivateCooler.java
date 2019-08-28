package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_ActivateCooler extends InstantCommand {
    public C_ActivateCooler() {
        super(() -> Robot.getCooler().activateCooler());
    }
}
