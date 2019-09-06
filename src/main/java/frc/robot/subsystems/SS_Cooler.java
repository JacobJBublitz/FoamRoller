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
        System.out.println("Activating cooler");
        coolerSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void deactivateCooler() {
        System.out.println("Deactivating cooler");
        coolerSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
