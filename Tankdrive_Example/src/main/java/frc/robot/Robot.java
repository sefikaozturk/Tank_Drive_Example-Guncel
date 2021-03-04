// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive myRobot;
  private Joystick stick;
  private PWMVictorSPX intakeMotor;
  private JoystickButton button1;

  @Override
  public void robotInit() {
   myRobot = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1));
   stick= new Joystick(0);
   intakeMotor = new PWMVictorSPX(4);
   button1 = new JoystickButton(stick, 1);
   

  }

  @Override
  public void teleopPeriodic() {
    //Hız
    double hiz = stick.getThrottle();
    if (stick.getThrottle() > 0) {
      hiz = 0;
    }

    myRobot.arcadeDrive( hiz* stick.getY(), -1*hiz*stick.getX());
    
    //IntakeMotor 
    if (hiz * stick.getY() < 0) {
      intakeMotor.set(-1);
    }
    else if (button1.get()) {
      intakeMotor.set(1);
    }
    else {
      intakeMotor.set(0);
    }
  
  }
 }
