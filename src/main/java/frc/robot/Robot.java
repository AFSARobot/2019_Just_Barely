/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Servo;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static SlideSteer strafeDrive;
  private static Grab grabber = new Grab(new WPI_TalonSRX(8)); //Adjust for actual ID
  private static OI Operation = new OI();
  private static Rangefinder rangeFinder = new Rangefinder(0);
  private static Lift elevator = new Lift(new WPI_TalonSRX(5));
  private static Ramp sharpIncline = new Ramp(new WPI_TalonSRX(6), new WPI_TalonSRX(7));
  private static boolean InFullDriveMode;
  private static boolean HoldAngleDrive;
  private static WPI_TalonSRX s1 = new WPI_TalonSRX(1),
    s2 = new WPI_TalonSRX(2),
    s3 = new WPI_TalonSRX(3),
    s4 = new WPI_TalonSRX(4);
  private Servo GrabServo;
  private static PigeonIMU pigeon = new PigeonIMU(s1);
//WPI_TalonSRX
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    InFullDriveMode = false;
    HoldAngleDrive = false;
    strafeDrive = new SlideSteer(s1, s2, s3, s4);
    CameraServer.getInstance().startAutomaticCapture();
    GrabServo = new Servo(5);
    pigeon.setYaw(0);
    pigeon.setFusedHeading(0);
  }


  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Rangefinder Distance ", rangeFinder.getRange());

    double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    SmartDashboard.putNumber("Pigeon Yaw", ypr[0]);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    
    pigeon.setYaw(0);
    pigeon.setFusedHeading(0);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if (InFullDriveMode == true) {
      strafeDrive.FullDrive(Operation.get_Xaxis(), Operation.get_Yaxis(), Operation.get_Zaxis());
      SmartDashboard.putString("Drive Mode", "Full Drive");
    }
    else if (HoldAngleDrive == true) {
      double[] ypr = new double[3];
      pigeon.getYawPitchRoll(ypr);
      Double Pigeonface = ypr[0];
      Double Adjust = Pigeonface;
      if (Pigeonface > 180) {
      Adjust = 360 - Pigeonface; 
      }

      strafeDrive.FullDrive(Operation.get_Xaxis(), Operation.get_Yaxis(), Adjust);
      SmartDashboard.putString("Drive Mode","Hold Angle");
    }
    else if (Operation.get_Turnbutton()) {
      strafeDrive.AntiStrafe(Operation.get_Yaxis());
      SmartDashboard.putString("Drive Mode", "Anti-Strafe");
    } else {
       strafeDrive.DriveFunction(Operation.get_Xaxis(), Operation.get_Yaxis());
       SmartDashboard.putString("Drive Mode", "Strafe");
    }
    
    if (Operation.get_GrabButton()){
      if (grabber.currentState == Grab.State.released){
        grabber.collect();
      }
      else{
        grabber.release();
      }
    }
    else{
      grabber.operate();
    }
    if (Operation.get_FullDriveToggle()) {
    InFullDriveMode = !InFullDriveMode;
    }
    if (Operation.get_Slider()> 0 && Operation.get_Slider()< 1) {
    GrabServo.set(Operation.get_Slider());
    
  }
    if (Operation.get_HoldAngleDrive()) {
      HoldAngleDrive = !HoldAngleDrive;
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
