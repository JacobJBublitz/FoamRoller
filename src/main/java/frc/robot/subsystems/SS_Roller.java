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
    private static NetworkTableEntry cutoutCountEntry;

    private DigitalInput endOfRoll;
    private CANSparkMax feeder;

    private Encoder rollerEncoder = new Encoder(RobotMap.ROLLER_ENCODER_A, RobotMap.ROLLER_ENCODER_B);
    private DigitalInput cutoutSensor = new DigitalInput(RobotMap.FINISHED_PART_DETECTOR);
    private int cutoutCounts = 0;

    static {
        ShuffleboardTab tab = Shuffleboard.getTab("Status");
        rollerDistanceEntry = tab.add("Roller Distance", 0.0)
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();
        hasMaterialEntry = tab.add("Has Material", false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .getEntry();
        cutoutDetectedEntry = tab.add("Cutout Detected", false)
                .withWidget(BuiltInWidgets.kBooleanBox)
                .getEntry();
        cutoutCountEntry = tab.add("Cutout Count", 0)
                .withWidget(BuiltInWidgets.kTextView)
                .getEntry();
    }

    public SS_Roller() {
        endOfRoll = new DigitalInput(RobotMap.MATERIAL_DETECTOR);
        feeder = new CANSparkMax(RobotMap.FEEDER_MOTOR, MotorType.kBrushless);
        feeder.setInverted(true);

        cutoutSensor.requestInterrupts(new InterruptHandlerFunction<>() {
            @Override
            public void interruptFired(int interruptAssertedMask, Object param) {
                cutoutCounts++;
            }
        });
        cutoutSensor.setUpSourceEdge(false, true);
        cutoutSensor.enableInterrupts();

        rollerEncoder.setDistancePerPulse(1.0 / 256.0);
    }

    @Override
    public void periodic() {
        rollerDistanceEntry.setNumber(getRollerDistance());
        hasMaterialEntry.setBoolean(hasMaterial());
        cutoutDetectedEntry.setBoolean(cutoutSensor.get());
        cutoutCountEntry.setNumber(getCutoutCount());
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
