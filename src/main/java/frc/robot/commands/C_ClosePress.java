package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_ClosePress extends InstantCommand {
    public C_ClosePress() {
        super(() -> Robot.getPress().closePress());
    }

    @Override
    protected void initialize() {
        super.initialize();

        System.out.println("Closing Press");
    }
}
