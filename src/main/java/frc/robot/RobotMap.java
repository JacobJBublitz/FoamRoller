/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Motors
  public static final int FEEDER_MOTOR = 11;

  //DIO
  public static final int ROLLER_SWITCH = 0;
  public static final int ESTOP = 28; //TODO:
  

  //
  public static final int OVEN_UP = 3;
  public static final int OVEN_DOWN = 2;

  public static final int PRESS_FORWARD = 1;
  public static final int PRESS_REVERSE = 0;



}
