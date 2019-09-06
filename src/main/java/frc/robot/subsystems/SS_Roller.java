package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class SS_Roller extends Subsystem {
    private DigitalInput endOfRoll;

    private CANSparkMax feeder;

    private Encoder rollerEncoder = new Encoder(RobotMap.ROLLER_ENCODER_A, RobotMap.ROLLER_ENCODER_B);
    private DigitalInput cutoutSensor = new DigitalInput(RobotMap.FINISHED_PART_DETECTOR);
    private Counter cutoutCounter = new Counter(cutoutSensor);

    public SS_Roller() {
        endOfRoll = new DigitalInput(RobotMap.MATERIAL_DETECTOR);
        feeder = new CANSparkMax(RobotMap.FEEDER_MOTOR, MotorType.kBrushless);
        feeder.setInverted(true);

        rollerEncoder.setDistancePerPulse(1.0 / 256.0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Roller Distance", getRollerDistance());
        SmartDashboard.putBoolean("Has Material", hasMaterial());
        SmartDashboard.putBoolean("Cutout Detected", cutoutSensor.get());
        SmartDashboard.putNumber("Cutout Count", getCutoutCount());
    }

    public void setSpeed(double speed) {
        feeder.set(speed);
    }

    public boolean hasMaterial() {
        return !endOfRoll.get();
    }

    public double getRollerDistance() {
        return rollerEncoder.getDistance();
    }

    public void resetRollerDistance() {
        rollerEncoder.reset();
    }

    public int getCutoutCount() {
        return cutoutCounter.get();
    }

    public void resetCutoutCount() {
        cutoutCounter.reset();
    }

    @Override
    public void initDefaultCommand() {
    }
}
