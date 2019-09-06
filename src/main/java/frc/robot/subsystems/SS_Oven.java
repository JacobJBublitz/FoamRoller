package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class SS_Oven extends Subsystem {
    private final DoubleSolenoid ovenSolenoid = new DoubleSolenoid(RobotMap.OVEN_UP, RobotMap.OVEN_DOWN);

    private final DigitalInput paperDetector = new DigitalInput(RobotMap.OVEN_PAPER_DETECTOR);

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Has Paper", isDetectingPaper());
    }

    public void closeOven(boolean state) {
        if (state) {
            System.out.println("Closing oven");
            ovenSolenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            System.out.println("Opening oven");
            ovenSolenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

    public boolean isDetectingPaper() {
        return !paperDetector.get();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
