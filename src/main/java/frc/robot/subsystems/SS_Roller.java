/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
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
  private DoubleSolenoid press;

  public SS_Roller(){
    endOfRoll = new DigitalInput(RobotMap.ROLLER_SWITCH);
    feeder = new CANSparkMax(RobotMap.FEEDER_MOTOR, MotorType.kBrushless);

    oven = new DoubleSolenoid(RobotMap.OVEN_UP, RobotMap.OVEN_DOWN);
    press = new DoubleSolenoid(RobotMap.PRESS_FORWARD, RobotMap.PRESS_REVERSE);

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
  public void pressPress(boolean state) {
    if(state){
      press.set(DoubleSolenoid.Value.kReverse);
    } else {
      press.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean getEndOfRoll(){
    return endOfRoll.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
