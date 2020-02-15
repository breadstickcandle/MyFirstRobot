/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import Robot class
import frc.robot.RobotMap;

public class TankDrive extends Command {
  //extends Command (counts as a command)
  //inheretence: inhereting stuff from another class (subclasses inherit methods from superclass)
  //extends = inherit from...
  public TankDrive() {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //TankDrive = One axis Controls One Side of the DriveTrain
    double leftStickY = Robot.m_oi.GetDriverRawAxis(RobotMap.LEFT_STICK_Y);
    double rightStickY = Robot.m_oi.GetDriverRawAxis(RobotMap.RIGHT_STICK_Y);

    Robot.driveTrain.setLeftMotors(leftStickY);
    Robot.driveTrain.setRightMotors(rightStickY);
  //Takes leftStickY sets it to the right motors, and takes the Right Stick y to the RIght Motors
  //everytime through a robot period (TeleopPeriodic, Scheduler.getInstance is run)
  //Might be very sensitive; Because GetRawAxis in OI returns value between 0 and 1; 1 is FULL
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //EVERYTIME THROUGH THE LOOP, THIS IS CALLED IMMEDIATELY AFTER IT FINISHES "EXECUTE"
    //(EXECUTE TO ISFINISHED)
    //FALSE MEANS THIS COMMAND NEVER ENDS WHICH IS GOOD.
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //END. MOTOR CONTROLLERS PRESERVE THE LAST INPUT GIVEN SO IF
    //NICE WAY IF ANYTHING ELSE IS HAPPENING WITH THE DRIVE TRAIN, WE SHOULD STOP IT FIRST.
      Robot.driveTrain.setLeftMotors(0);
      Robot.driveTrain.setRightMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
    //FILE IN HERE DOES THE SAME THING AS END?
    //REFERENCES SAME OBJECT?//INSTANCE VARIABLE IN THE CURRENT CLASS
  }
}
