package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.InterruptJNI;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SS_Roller extends Subsystem {
    private static NetworkTableEntry rollerDistanceEntry;
    private static NetworkTableEntry hasMaterialEntry;
    private static NetworkTableEntry cutoutDetectedEntry;
    private static NetworkTableEntry totalCutoutEntry;

    private DigitalInput endOfRoll;
    private CANSparkMax feeder;

    private Encoder rollerEncoder = new Encoder(RobotMap.ROLLER_ENCODER_A, RobotMap.ROLLER_ENCODER_B);
    private DigitalInput cutoutSensor = new DigitalInput(RobotMap.FINISHED_PART_DETECTOR);
    private int cutoutCounts = 0;

    private int totalCutouts = 0;

    private boolean detectingCutout = false;
    private double lastHardwareDetectionTimestamp = 0.0;

    static {
        ShuffleboardTab tab = Shuffleboard.getTab("Status");
        rollerDistanceEntry = tab.add("Roller Distance", 0.0)
                .withWidget(BuiltInWidgets.kTextView)
                .withSize(2, 2)
                .withPosition(8, 0)
                .getEntry();
        hasMaterialEntry = tab.add("Has Material", false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withSize(2, 2)
                .withPosition(2, 0)
                .getEntry();
        cutoutDetectedEntry = tab.add("Cutout Detected", false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .withSize(2, 2)
                .withPosition(4, 0)
                .getEntry();
        totalCutoutEntry = tab.add("Total Cutouts", 0)
                .withWidget(BuiltInWidgets.kTextView)
                .withSize(2, 2)
                .withPosition(6, 0)
                .getEntry();
    }

    public SS_Roller() {
        endOfRoll = new DigitalInput(RobotMap.MATERIAL_DETECTOR);
        feeder = new CANSparkMax(RobotMap.FEEDER_MOTOR, MotorType.kBrushless);
        feeder.setInverted(true);

        rollerEncoder.setDistancePerPulse(1.0 / Constants.ROLLER_TICKS_PER_DISTANCE);
    }

    @Override
    public void periodic() {
        rollerDistanceEntry.setNumber(getRollerDistance());
        hasMaterialEntry.setBoolean(hasMaterial());
        cutoutDetectedEntry.setBoolean(detectingCutout);
        totalCutoutEntry.setNumber(totalCutouts);

        if (isHardwareDetectingCutout()) {
            if (!detectingCutout) {
                if (lastHardwareDetectionTimestamp < 0) {
                    lastHardwareDetectionTimestamp = Timer.getFPGATimestamp();
                } else if (Timer.getFPGATimestamp() - lastHardwareDetectionTimestamp > Constants.CUTOUT_DEBOUNCE_TIME) {
                    cutoutCounts++;
                    totalCutouts++;
                    detectingCutout = true;
                }
            } else {
                lastHardwareDetectionTimestamp = -1;
            }
        } else {
            lastHardwareDetectionTimestamp = -1;
            detectingCutout = false;
        }
    }

    public void resetTotalCutouts() {
        totalCutouts = 0;
    }

    public int getTotalCutouts() {
        return totalCutouts;
    }

    private boolean isHardwareDetectingCutout() {
        return cutoutSensor.get();
    }

    public void setSpeed(double speed) {
        feeder.set(speed);
    }

    public boolean hasMaterial() {
        return endOfRoll.get();
    }

    public double getRollerDistance() {
        return rollerEncoder.getDistance();
    }

    public void resetRollerDistance() {
        rollerEncoder.reset();
    }

    public int getCutoutCount() {
        return cutoutCounts;
    }

    public void resetCutoutCount() {
        cutoutCounts = 0;
    }

    @Override
    public void initDefaultCommand() {
    }
}
