package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SS_FailureLight extends Subsystem {
    private static NetworkTableEntry estopEntry;

    private Relay failureIndicator = new Relay(RobotMap.FAILURE_LIGHT);

    private DigitalInput estopButton = new DigitalInput(RobotMap.ESTOP);
    private boolean estopped = false;

    static {
//        ShuffleboardTab tab = Shuffleboard.getTab("Status");
//        estopEntry = tab.add("EStopped", false)
//                .withWidget(BuiltInWidgets.kBooleanBox)
//                .getEntry();
    }

    @Override
    public void periodic() {
//        boolean estopValue = !estopButton.get();
//        if (estopValue && !estopped) {
//            estopped = true;
//            setFailure(true);
//
//            Robot.getRoller().setSpeed(0.0);
//            Robot.getPress().endClear();
//            System.out.println("Estopping!");
//            Scheduler.getInstance().removeAll();
//        }
//        estopEntry.setBoolean(estopped);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void setFailure(boolean failure) {
        if (failure) {
            failureIndicator.set(Relay.Value.kForward);
        } else {
            failureIndicator.set(Relay.Value.kOff);
        }
    }

    public void clearEstop() {
        estopped = false;
    }
}
