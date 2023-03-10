// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_VictorSPX m_leftFrontMotor = new WPI_VictorSPX(5);
  private final WPI_VictorSPX m_rightFrontMotor = new WPI_VictorSPX(3);
  private final WPI_VictorSPX m_leftRearMotor = new WPI_VictorSPX(6);
  private final WPI_VictorSPX m_rightRearMotor = new WPI_VictorSPX(2);

  private final XboxController m_stick = new XboxController(0);

  private final MotorControllerGroup leftMotors = new MotorControllerGroup(m_leftFrontMotor, m_leftRearMotor);
  private final MotorControllerGroup rightMotors = new MotorControllerGroup(m_rightFrontMotor, m_rightRearMotor);
  private DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightFrontMotor.setInverted(true);
    m_rightRearMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.

    if(Math.abs(m_stick.getLeftY()) > 0.2 || Math.abs(m_stick.getLeftX()) > 0.2)
    {
      diffDrive.arcadeDrive(-m_stick.getLeftY() * 0.5, -m_stick.getLeftX() * 0.5);
    }
    else
    {
      diffDrive.arcadeDrive(-m_stick.getRightY(), -m_stick.getRightX());
    }
    
  }
}
