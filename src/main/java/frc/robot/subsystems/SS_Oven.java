package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class SS_Oven extends Subsystem {
    private static NetworkTableEntry hasPaperEntry;

    private final DoubleSolenoid ovenSolenoid = new DoubleSolenoid(RobotMap.OVEN_UP, RobotMap.OVEN_DOWN);

    private final DigitalInput paperDetector = new DigitalInput(RobotMap.OVEN_PAPER_DETECTOR);

    static {
        ShuffleboardTab tab = Shuffleboard.getTab("Status");

        hasPaperEntry = tab.add("Has Paper", false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withSize(2, 2)
                .withPosition(0, 0)
                .getEntry();
    }

    @Override
    public void periodic() {
        hasPaperEntry.setBoolean(isDetectingPaper());
    }

    public void closeOven(boolean state) {
        if (state) {
            System.err.println("Closing oven");
            ovenSolenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            System.err.println("Opening oven");
            ovenSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

    public boolean isDetectingPaper() {
        return paperDetector.get();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
