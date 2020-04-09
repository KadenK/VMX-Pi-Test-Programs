/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

#include <frc/Joystick.h>
#include <frc/PWMVictorSPX.h>
#include <frc/TimedRobot.h>
#include <ctre/phoenix/motorcontrol/can/WPI_TalonSRX.h>
#include <ctre/phoenix/cci/Unmanaged_CCI.h>
#include <frc/SPI.h>
#include "AHRS.h"
#include <frc/SpeedControllerGroup.h>


using namespace frc;
using namespace std;
using namespace ctre::phoenix::motorcontrol::can;
//ClydeDrive

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * Joystick analog values range from -1 to 1 and speed controller inputs as
 * range from -1 to 1 making it easy to work together.
 */
class Robot : public frc::TimedRobot {
 public:

  void RobotPeriodic() override {
    talon1.Feed();
    talon2.Feed();
    talon3.Feed();
    //talon4.Feed();
	  c_FeedEnable(100);
  }

  void TeleopInit() override {

  }

  void TeleopPeriodic() override {

  printf("Teleop Periodic\n");


  double vY = stick.GetY() * -1;
  double vRot = stick.GetRawAxis(4) * -1;

  printf("vY %f   vRot %f \n", vY, vRot);

  double left = vY + vRot;
  double right = vY - vRot;

  if (abs(left) > 1) {
    left = 1 * abs(left) / left;
  }

  if (abs(right) > 1) {
    right = 1 * abs(right) / right;
  }

  talon1.Set(left);
  talon2.Set(left);
  talon3.Set(right);
  //talon4.Set(right);


  }

 private:
  Joystick stick{0};
  WPI_TalonSRX talon1{1};
  WPI_TalonSRX talon2{2};
  WPI_TalonSRX talon3{3};
  //WPI_TalonSRX talon4{4};
  AHRS ahrs{SPI::Port::kMXP};
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
