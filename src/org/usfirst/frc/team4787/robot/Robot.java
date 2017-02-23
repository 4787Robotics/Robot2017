
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
	final double DEADZONEX = 0.07, DEADZONEY = 0.07;
    final int MECH_DOOR_OPEN = 3, MECH_DOOR_CLOSE = 5, CLIMBING_MECH_UP = 4, CLIMBING_MECH_DOWN = 6;
    
	

	
    Joystick stick = new Joystick(0);
    Talon bLeft = new Talon(1);
    Talon fLeft = new Talon(2);
    Talon bRight= new Talon(3);
    Talon fRight = new Talon(4);
    Servo rightGearDoorServo = new Servo(5);
    Servo leftGearDoorServo = new Servo(6);
    Spark climbingMech = new Spark(7);
    RobotDrive arcadeDrive = null;
    
    
    boolean initRobotDrive = true;
    String autoMode = "M";
    double y, x, expY, expX, autoPower, autoTime, leftDoorAngleStart, rightDoorAngleStart;
  
    
    public Robot() {
    	stick = new Joystick(0);
    	Timer.delay(0.05);
    }
    
    /**
     * 
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
		bRight.set(-autoPower);
		fRight.set(-autoPower);
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
    			autoPower = 0.6;
    			autoTime = 1.33;
    			bLeft.set(autoPower);
    			fLeft.set(autoPower);
    			bRight.set(-autoPower);
    			fRight.set(-autoPower);
    			Timer.delay(autoTime);
    			bLeft.set(autoPower);
    			fLeft.set(autoPower);
    			bRight.set(autoPower);
    			fRight.set(autoPower);
    			autoTime = 0.467;
    			Timer.delay(autoTime/2);
    			bLeft.set(0);
    			fLeft.set(0);
    			bRight.set(0);
    			fRight.set(0);
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
    	if(initRobotDrive || arcadeDrive.equals(null)) {
        	arcadeDrive = new RobotDrive(fLeft, bLeft, fRight, bRight);
        	initRobotDrive = false;
        }
    	while (isOperatorControl() && isEnabled()) {
    		arcadeDrive.arcadeDrive(-stick.getY(), -stick.getX());
    		boolean openMechDoor = stick.getRawButton(MECH_DOOR_OPEN);
        	boolean closeMechDoor = stick.getRawButton(MECH_DOOR_CLOSE);
        	boolean climbUpRope = stick.getRawButton(CLIMBING_MECH_UP);
        	boolean climbDownRope = stick.getRawButton(CLIMBING_MECH_DOWN);
        	if (climbUpRope) {
				climbingMech.set(0.2); //don't know what to set this to
			} 
			if (climbDownRope) {
				climbingMech.set(-0.2); //don't know what to set this to
			}
        	if (openMechDoor) {
        		leftGearDoorServo.setAngle(leftGearDoorServo.getAngle() + 90); //might have to reverse this with the one below
        		rightGearDoorServo.setAngle(rightGearDoorServo.getAngle() - 90);
    		} 
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
    
    public void disabled()
    {
    	this.stopMotors();
    	rightGearDoorServo.setDisabled();
    	leftGearDoorServo.setDisabled();
    	climbingMech.set(0);
    	System.out.println("I prefer differently abled you ableist");
    }
    
    /*
     * fLeft.set(stick.getX() - (stick.getY() * 0.5));
		bLeft.set(stick.getX() - (stick.getY() * 0.5));
		fRight.set(stick.getX() + (stick.getY() * 0.5));
		bRight.set(stick.getX() + (stick.getY() * 0.5));
     */
}
