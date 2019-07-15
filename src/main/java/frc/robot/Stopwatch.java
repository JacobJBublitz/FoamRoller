/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Stopwatch {
    private long lastTime;
    private long pauseTime;
    private boolean paused = false;


    public Stopwatch() {
        reset();
    }

    /**
     * Reset the stopwatch to measure from the current time.
     */
    public void reset() {
        lastTime = System.currentTimeMillis();
    }
    public void pause() {
        paused = true;
        pauseTime= System.currentTimeMillis();
    }
    public void play(){
        if(paused){
            lastTime -= (System.currentTimeMillis() - pauseTime);
            paused = false;
        }
    }

    /**
     * Return the elapsed time in milliseconds
     */
    public long getMilliseconds() {
        return System.currentTimeMillis() - lastTime;
    }

    /**
     * Return the elapsed time in seconds
     */
    public double getSeconds() {
        return getMilliseconds() / 1000.0;
    }
    public double getMinutes() {
        return getSeconds() / 60.0;
    }
}