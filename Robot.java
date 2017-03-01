
package org.usfirst.frc.team4787.robot;

//2017

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;
//import java.util.Random;



/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	// min value in x and y direction for robot to respond to joystick movements
	final double DEADZONEX = 0.07, DEADZONEY = 0.07;
	
    // button configuration
	final int GEAR_MECH_DOOR_OPEN = 3, GEAR_MECH_DOOR_CLOSE = 5, CLIMBING_MECH_CLIMB = 4;
    
    // 0 = joystick usb port in driver station
    Joystick stick = new Joystick(0);
    
    // number in parenthesis for Talon, Servo, and Spark constructor are PWM ports on roborio
    Talon bLeft = new Talon(1);
    Talon fLeft = new Talon(2);
    Talon bRight= new Talon(3);
    Talon fRight = new Talon(4);
    Servo rightGearDoorServo = new Servo(5);
    Servo leftGearDoorServo = new Servo(6);
    Spark climbingMech = new Spark(7);
    
    // sets RobotDrive obj to null so that auto code works
    RobotDrive arcadeDrive = null;
    
    // variable to handle initialization of Robot Drive obj
    boolean initRobotDrive;
    
    // String variable to determine auto mode, "L" = left starting position, "M" = middle starting position, "R" = right starting posistion
    String autoMode;
    
    // various variables to control robot mechanisms and drive train
    double y, x, expY, expX, autoPower, autoTime, leftDoorAngleStart, rightDoorAngleStart;
  
    
    public Robot() {
    	initRobotDrive = true;
        autoMode = "M";
    	Timer.delay(0.05);
    }
    
    /**
     * Method for robot to cross baseline in auto
     */
    public void crossBaseLine() {
    	autoPower = 0.4;
		autoTime = 2;
		bLeft.set(autoPower);
		fLeft.set(autoPower);
		bRight.set(-autoPower);
		fRight.set(-autoPower);
		Timer.delay(autoTime);
    }
    /**
     * Method for robot to do a 180 degree turn in auto
     */
    public void turnAround() {
    	autoPower = 0.4;
		autoTime = 0.7;
		bLeft.set(autoPower);
		fLeft.set(autoPower);
		bRight.set(autoPower);
		fRight.set(autoPower);
		Timer.delay(autoTime);
    }
    
    /**
     * Method for robot to turn left in auto
     */
    public void turnLeft() {
    	autoPower = 0.4;
		autoTime = 0.35;
		bLeft.set(autoPower);
		fLeft.set(autoPower);
		bRight.set(autoPower);
		fRight.set(autoPower);
		Timer.delay(autoTime);
    }
    
    /**
     * method for robot to turn right in auto
     */
    public void turnRight() {
    	autoPower = 0.4;
		autoTime = 0.35;
		bLeft.set(-autoPower);
		fLeft.set(-autoPower);
		bRight.set(-autoPower);
		fRight.set(-autoPower);
		Timer.delay(autoTime);
    }
    
    /**
     * Method to stop motors in general
     */
    public void stopMotors() {
    	bLeft.set(0);
		fLeft.set(0);
		bRight.set(0);
		fRight.set(0);
    }
    
    
    /**
     * AUTONOMOUS
     */
    public void autonomous() {
    	switch(autoMode) {
    		case "L":
    			this.crossBaseLine();
    			this.stopMotors();
    			break;
    		case "M":
    			this.crossBaseLine();
    			this.stopMotors();
    			break;
    		case "R":
    			this.crossBaseLine();
    			this.stopMotors();
    			break;
    		default:
    			this.stopMotors();
    			break;
    	}		
    }

    /**
     * TELEOPERATED MODE. Runs the motors with arcade steering.
     */
    public void operatorControl() {
    	// code to initialize RobotDrive in tele op mode
    	if(initRobotDrive || arcadeDrive.equals(null)) {
        	arcadeDrive = new RobotDrive(fLeft, bLeft, fRight, bRight);
        	initRobotDrive = false;
        }
    	// teleop code loop
    	while (isOperatorControl() && isEnabled()) {
    		// programs motor controllers to drive with arcade drive
    		arcadeDrive.arcadeDrive(-stick.getY(), -stick.getX());
    		
    		// retrieves whether a button is pressed down or not
    		boolean openMechDoor = stick.getRawButton(GEAR_MECH_DOOR_OPEN);
        	boolean closeMechDoor = stick.getRawButton(GEAR_MECH_DOOR_CLOSE);
        	boolean climbUpRope = stick.getRawButton(CLIMBING_MECH_CLIMB);
        	
        	// if CLIMBING_MECH_CLIMB button is pressed, spin climbing mech so robot climbs
        	if (climbUpRope) {
				climbingMech.set(0.2); //don't know what to set this to
			} 
        	
        	// if GEAR_MECH_DOOR_OPEN button is pressed, open gear mech door so gear falls out
        	if (openMechDoor) {
        		leftGearDoorServo.setAngle(leftGearDoorServo.getAngle() + 90); //might have to reverse this with the one below
        		rightGearDoorServo.setAngle(rightGearDoorServo.getAngle() - 90);
    		}
        	
        	// if GEAR_MECH_DOOR_CLOSE button is pressed, close gear mech door so gears will fall in and stay in place on gear mech
    		if (closeMechDoor) {
    			leftGearDoorServo.setAngle(leftGearDoorServo.getAngle() - 90); //might have to reverse this with the one above
        		rightGearDoorServo.setAngle(rightGearDoorServo.getAngle() + 90);
    		}
        	
        	// Old Driving Code
//        	x = stick.getX();
//        	y = stick.getY();
//        	expX = 0;
//        	expY = 0;
//        	expX = Math.abs(x) > DEADZONEX ? x * Math.abs(x) : 0;
//        	expY = Math.abs(y) > DEADZONEY ? y * Math.abs(y) : 0;
//        	expX = Math.abs(x) > DEADZONEX ? Math.pow(x, 3) : 0;
//        	expY = Math.abs(y) > DEADZONEY ? Math.pow(y, 3) : 0;
//        	bLeft.set(expX - expY);
//    		fLeft.set(expX - expY);
//    		bRight.set(expX + expY);
//    		fRight.set(expX + expY);
    				
    	    Timer.delay(0.005);		// wait for a motor update time
        }
    }

    
    /**
     * Runs during test mode
     */
    public void test() {
    	System.out.println("test function");
    }
    
    /**
     * Runs while robot is disabled
     */
    public void disabled()
    {
    	// disables all motors and servos on the robot
    	this.stopMotors();
    	rightGearDoorServo.setDisabled();
    	leftGearDoorServo.setDisabled();
    	climbingMech.set(0);
    	System.out.println("I prefer differently abled you ableist");
    }
}
