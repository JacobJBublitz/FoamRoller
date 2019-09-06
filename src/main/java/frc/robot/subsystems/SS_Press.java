package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SS_Press extends Subsystem {
    private final DoubleSolenoid pressSolenoid = new DoubleSolenoid(RobotMap.PRESS_FORWARD, RobotMap.PRESS_REVERSE);

    @Override
    protected void initDefaultCommand() {

    }

    public void openPress() {
        System.out.println("Opening press");
        pressSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void closePress() {
        System.out.println("Closing press");
        pressSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
