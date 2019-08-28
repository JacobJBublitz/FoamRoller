package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SS_Cooler extends Subsystem {
    private final DoubleSolenoid coolerSolenoid = new DoubleSolenoid(RobotMap.COOLER_ON, RobotMap.COOLER_OFF);

    @Override
    protected void initDefaultCommand() {

    }

    public void activateCooler() {
        coolerSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void deactivateCooler() {
        coolerSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
