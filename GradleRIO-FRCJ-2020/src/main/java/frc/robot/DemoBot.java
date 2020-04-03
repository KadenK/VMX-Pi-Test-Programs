/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.swing.event.HyperlinkEvent;

import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj.Spark;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DemoBot extends TimedRobot {


    public Spark rightf;
    public Spark rightb;
    public Spark leftf;
    public Spark leftb;

    public Encoder fright;
    public Encoder bright;
    public Encoder fleft;
    public Encoder bleft;

    private Joystick idkimnotsure;

    private AHRS navx;
    //\\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/private MecanumDrive mcDrive;



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    rightf = new Spark(0);
    rightb = new Spark(3);
    leftf = new Spark(1);
    leftb = new Spark(2);
    fright = new Encoder(0,1);
    bright = new Encoder(6,7);
    fleft = new Encoder (2,3);
    bleft = new Encoder(4,5);
    rightf.setInverted(true);
    rightb.setInverted(true);

    idkimnotsure = new Joystick(0);
    navx = new AHRS();

  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {


  }
  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    //mcDrive = new MecanumDrive(leftf, leftb, rightf, rightb);

  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {


    
    double xdirect = idkimnotsure.getX();
    double ydirect = idkimnotsure.getY();
    double rotation = idkimnotsure.getRawAxis(4);
    /*if (idkimnotsure.getRawButton(2)) {
      navx.zeroYaw();
    }*/

    SmartDashboard.putNumber("Unmodified xdirect", xdirect);
    SmartDashboard.putNumber("Unmodified ydirect", ydirect);

    SmartDashboard.putNumber("rotation", rotation);
    
    double yaw = navx.getYaw();
    SmartDashboard.putNumber("yaw", yaw);
    //mcDrive.driveCartesian(ydirect, xdirect, rotation, yaw);
    /*double va = getAngle(xdirect, ydirect);
    double hype = hype(xdirect, ydirect);
    double cva = va - yaw;
    if (Math.abs(cva) > 180) {
      cva= va-360*Math.abs(va)/va;
    }
    double cx = hype * Math.cos(Math.toRadians(cva));
    double cy = hype * Math.sin(Math.toRadians(cva));

    xdirect = cx;
    ydirect = cy;

    SmartDashboard.putNumber("yaw", yaw);
    SmartDashboard.putNumber("xdirect", xdirect);
    SmartDashboard.putNumber("ydirect", ydirect);*/
    


    double rfPower = clip(ydirect + rotation + xdirect);
    double rbPower = clip(ydirect + rotation - xdirect);
    double lfPower = clip(ydirect - rotation - xdirect);
    double lbPower = clip(ydirect - rotation + xdirect);

    rightf.set(rfPower);
    rightb.set(rbPower);
    leftf.set(lfPower);
    leftb.set(lbPower);
    

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private double clip (double value) {
    if (Math.abs(value)>1){
        value = value/Math.abs(value);
    }
    return value;
  }

  //fod test

     public double hype (double x, double y) {

      return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
      
    }


    private double getAngle (double x, double y){
       
      double va = 0;

      if (y > 0) {
         va = Math.toDegrees(Math.asin(Math.abs(x)/hype(x,y)));
      }
      if (y < 0){
         va = (Math.toDegrees(Math.acos(Math.abs(x)/hype(x,y))) + 90);
      }

      if (x < 0){
        va = va*-1;
      }

      return va;

    }

    public void derivedis () {

    }

    public void powow (double pow) {
      rightf.set(pow);
      rightb.set(pow);
      leftf.set(pow);
      leftb.set(pow);
    }
    public double codeP (){
      fright.setDistancePerPulse(1);
      bright.setDistancePerPulse(1);
      bleft.setDistancePerPulse(1);
      fleft.setDistancePerPulse(1);

      double codeP = (fright.getDistance() + bright.getDistance() + bleft.getDistance() + fleft.getDistance())/4;
      
      
      return codeP;
    }


}
