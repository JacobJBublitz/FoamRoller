package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class C_AdvanceRoller extends Command {
    private final double speed;
    private final double targetDistance;

    public C_AdvanceRoller(double speed, double targetDistance) {
        this.speed = speed;
        this.targetDistance = targetDistance;

        requires(Robot.getRoller());
    }

    @Override
    protected void initialize() {
        Robot.getRoller().resetRollerDistance();
        Robot.getRoller().setSpeed(speed);
    }

    @Override
    protected void execute() {
        // If the oven paper rips stop everything
        if (!Robot.getOven().isDetectingPaper()) {
            System.out.println("Detected a rip in the oven paper, halting.");
            new CG_Cleanup().start();
        }
    }

    @Override
    protected void end() {
        Robot.getRoller().setSpeed(0.0);
    }

    @Override
    protected boolean isFinished() {
        return Robot.getRoller().getRollerDistance() >= targetDistance;
    }
}
