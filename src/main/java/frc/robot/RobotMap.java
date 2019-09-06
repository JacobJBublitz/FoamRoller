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
  public static final int MATERIAL_DETECTOR = 0;
  public static final int OVEN_PAPER_DETECTOR = 1;
  public static final int FINISHED_PART_DETECTOR = 2;
  public static final int ESTOP = 28; //TODO:


  public static final int ROLLER_ENCODER_A = 8;
  public static final int ROLLER_ENCODER_B = 9;


  //
  public static final int OVEN_UP = 1;
  public static final int OVEN_DOWN = 0;

  public static final int PRESS_FORWARD = 3;
  public static final int PRESS_REVERSE = 2;


  public static final int COOLER_ON = 4;
  public static final int COOLER_OFF = 5;
}
