package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class SS_Press extends Subsystem {
    private final DoubleSolenoid pressSolenoid = new DoubleSolenoid(RobotMap.PRESS_FORWARD, RobotMap.PRESS_REVERSE);
    private final DoubleSolenoid clearerSolenoid = new DoubleSolenoid(RobotMap.PRESS_CLEARER_ON, RobotMap.PRESS_CLEARER_OFF);

    private int missedCutouts = 0;

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

    public void addMissedCutout() {
        missedCutouts++;
        System.out.println("Didn't detect the correct amount of cutouts");
    }

    public void resetMissedCutouts() {
        missedCutouts = 0;
    }

    public boolean missedToManyCutout() {
        return missedCutouts > Constants.ALLOWABLE_PRESS_MISSES;
    }

    public void startClear() {
        System.out.println("Clearing foam from press");
        clearerSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void endClear() {
        System.out.println("Done clearing foam from press");
        clearerSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
