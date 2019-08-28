package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SS_Roller extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DigitalInput endOfRoll;

  private CANSparkMax feeder;

  private DoubleSolenoid oven;

  private Encoder rollerEncoder = new Encoder(RobotMap.ROLLER_ENCODER_A, RobotMap.ROLLER_ENCODER_B);

  public SS_Roller(){
    endOfRoll = new DigitalInput(RobotMap.ROLLER_SWITCH);
    feeder = new CANSparkMax(RobotMap.FEEDER_MOTOR, MotorType.kBrushless);

    oven = new DoubleSolenoid(RobotMap.OVEN_UP, RobotMap.OVEN_DOWN);

    rollerEncoder.setDistancePerPulse(1.0 / 256.0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Roller Distance", rollerEncoder.getDistance());
  }

  public void setSpeed(double speed){
    feeder.set(speed);
  }

  public void closeOven(boolean state) {
    if(state){
      oven.set(DoubleSolenoid.Value.kReverse);
    } else {
      oven.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean getEndOfRoll(){
    return endOfRoll.get();
  }

  public double getRollerDistance() {
    return rollerEncoder.getDistance();
  }

  public void resetRollerDistance() {
    rollerEncoder.reset();
  }

  @Override
  public void initDefaultCommand() {
  }
}
