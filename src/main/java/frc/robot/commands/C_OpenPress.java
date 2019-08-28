package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class C_OpenPress extends InstantCommand {
    public C_OpenPress() {
        super(() -> Robot.getPress().openPress());
    }

    @Override
    protected void initialize() {
        super.initialize();

        System.out.println("Opening Press`");
    }
}
