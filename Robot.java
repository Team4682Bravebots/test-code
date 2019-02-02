/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private AnalogInput ai;
  private AnalogInput aia;
  private AnalogInput aib;
  private final double black = 2000;
  TalonSRX _talon1;
  TalonSRX _talon2;
  TalonSRX _talon3;
  TalonSRX _talon4;
  Joystick _joystick;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    ai = new AnalogInput(0);
    aia = new AnalogInput(1);
    aib = new AnalogInput(2);
    _talon1 = new TalonSRX(1);
    _talon2 = new TalonSRX(2);
    _talon3 = new TalonSRX(3);
    _talon4 = new TalonSRX(4);
    _joystick = new Joystick(0);
    
  
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
    SmartDashboard.putNumber("left",ai.getValue());
    SmartDashboard.putNumber("right",aia.getValue());
    SmartDashboard.putNumber("front",aib.getValue());
    


    // if all three are detected then tell smart dashboard values and mov motor 
    
    if (ai.getValue() >= black && aia.getValue() >= black && aib.getValue() >= black) { // &&
      SmartDashboard.putBoolean("black??", true);
      
      if (_joystick.getRawButton(1)) {
        _talon1.set(ControlMode.PercentOutput, 1);
        _talon2.set(ControlMode.PercentOutput, 1);
        _talon3.set(ControlMode.PercentOutput, 1);
        _talon4.set(ControlMode.PercentOutput, 1);

       
      }

      if (_joystick.getRawButton(2)) {
        _talon1.set(ControlMode.PercentOutput, 0);
        _talon2.set(ControlMode.PercentOutput, 0);
        _talon3.set(ControlMode.PercentOutput, 0);
        _talon4.set(ControlMode.PercentOutput, 0);

      }

      if (_joystick.getRawButton(9)){
        _talon1.set(ControlMode.PercentOutput, 1);
        _talon3.set(ControlMode.PercentOutput, 1);
        _talon2.set(ControlMode.PercentOutput, -1);
        _talon4.set(ControlMode.PercentOutput, -1);
      }

      

      
      


      

    }


    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
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
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
