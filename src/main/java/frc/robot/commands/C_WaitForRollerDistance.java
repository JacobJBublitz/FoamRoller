package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    protected boolean isFinished() {
        return Robot.getRoller().getRollerDistance() >= targetDistance;
    }
}
