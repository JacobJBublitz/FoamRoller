package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SS_Oven extends Subsystem {
    private final DoubleSolenoid ovenSolenoid = new DoubleSolenoid(RobotMap.OVEN_UP, RobotMap.OVEN_DOWN);

    private final DigitalInput paperDetector = new DigitalInput(RobotMap.OVEN_PAPER_DETECTOR);

    public void closeOven(boolean state) {
        if (state) {
            ovenSolenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
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
