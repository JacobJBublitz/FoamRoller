package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_SetRoller extends InstantCommand {
    public C_SetRoller(double speed) {
        super(() -> Robot.getRoller().setSpeed(speed));
    }

}
