/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class PWMServo extends TimedRobot {

    Servo servo;

    int time;


  @Override
  public void robotInit() {
      servo = new Servo(0);
  }

  @Override
  public void teleopPeriodic() {

    if (servo.get() < 0.1) {
        servo.set(1);
    }
    else if (servo.get() > 0.9) {
        servo.set(0);
    }
  }
}
