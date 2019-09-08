package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
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
        Robot.getRoller().resetCutoutCount();
    }

    @Override
    protected void end() {
        Robot.getRoller().setSpeed(0.0);
        if (Robot.getRoller().getCutoutCount() < Constants.EXPECTED_CUTOUT_COUNT) {
            Robot.getPress().addMissedCutout();
        }
    }

    @Override
    protected boolean isFinished() {
        return Robot.getRoller().getRollerDistance() >= targetDistance || !Robot.getOven().isDetectingPaper();
    }
}
