package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_WaitForRollerDistance extends Command {
    private final double targetDistance;

    public C_WaitForRollerDistance(double targetDistance) {
        this.targetDistance = targetDistance;
    }

    @Override
    protected void initialize() {
        Robot.getRoller().resetRollerDistance();
    }

    @Override
    protected void execute() {
        // If the oven paper rips stop everything
        if (!Robot.getOven().isDetectingPaper()) {
            Robot.getRoller().setSpeed(0.0);
            System.err.println("Detected a rip in the oven paper, stopping feed roller.");

            // TODO: Force the code to crash???
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.getRoller().getRollerDistance() >= targetDistance;
    }
}
