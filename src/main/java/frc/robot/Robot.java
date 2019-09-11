/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.CG_Cleanup;
import frc.robot.commands.C_Loop;
import frc.robot.subsystems.SS_FailureLight;
import frc.robot.subsystems.SS_Oven;
import frc.robot.subsystems.SS_Press;
import frc.robot.subsystems.SS_Roller;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private static SS_Press ss_press = new SS_Press();
    private static SS_Roller ss_roller = new SS_Roller();
    private static SS_Oven ss_oven = new SS_Oven();
    private static SS_FailureLight ss_failureLight = new SS_FailureLight();
    public static OI m_oi;

    public static SS_Roller getRoller() {
        return ss_roller;
    }

    public static SS_Press getPress() {
        return ss_press;
    }

    public static SS_Oven getOven() {
        return ss_oven;
    }

    public static SS_FailureLight getFailureLight() {
        return ss_failureLight;
    }

    private Command loopCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        m_oi = new OI();

        Shuffleboard.selectTab("Status");
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();

        System.err.println("\n\n\nEND");
        System.err.printf("%d piece(s) have been completed%n", Robot.getRoller().getTotalCutouts());
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        System.err.println("\n\n\nSTART");
        if (loopCommand != null) {
            loopCommand.cancel();
        }

        loopCommand = new C_Loop();
        loopCommand.start();
        Robot.getPress().resetMissedCutouts();
        Robot.getRoller().resetTotalCutouts();
        Robot.getFailureLight().setFailure(false);
        Robot.getFailureLight().clearEstop();
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        System.err.println("\n\n\nSTART");
        new CG_Cleanup().start();
        Robot.getFailureLight().setFailure(false);
        Robot.getFailureLight().clearEstop();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
