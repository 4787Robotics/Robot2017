package org.usfirst.frc.team4787.robot;

//2017

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Servo;
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
   // final int MECH_DOOR_OPEN = 5, MECH_DOOR_CLOSE = 3, CLIMBING_MECH_UP = 4, CLIMBING_MECH_DOWN = 6;
    
	

	
    Joystick stick;

    Talon bLeft = new Talon(1);
    Talon fLeft = new Talon(2);
    Talon bRight= new Talon(3);
    Talon fRight = new Talon(4);
    RobotDrive arcadeDrive;
    //Drive driveTrain;
    //SendableChooser<Integer> autoChooser;
    //TalonSRX gearMechDoor = new TalonSRX(5);
    //TalonSRC climbingMech = new TalonSRX(6);
    
    double y, x;
    double expY, expX;
    //int signY, signX;
  
    
    public Robot() {
    	stick = new Joystick(0);
        arcadeDrive = new RobotDrive(fLeft, bLeft, fRight, bRight);
        //Drive driveTrain = new Drive(arcadeDrive);
        Timer.delay(0.05);
//        autoChooser = new SendableChooser();
//        autoChooser.addDefault("Left Auto", 0);
//        autoChooser.addObject("Middle Auto", 1);
//        autoChooser.addDefault("Right Auto", 2);
//        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }
    
    

    /**
     * AUTONOMOUS
     */
    public void autonomous() {
    	double autoPower = 0.4;
    	double autoTime = 2;
    	bLeft.set(autoPower);
		fLeft.set(autoPower);
		bRight.set(-autoPower);
		fRight.set(-autoPower);
		Timer.delay(autoTime);
		bLeft.set(0);
		fLeft.set(0);
		bRight.set(0);
		fRight.set(0);
    	//Integer autoMode = Integer.parseInt(autoChooser.getSelected().toString());
    	//driveTrain.setMode("Autonomous");
//    	String autoMode = "L";
//    	double autoPower = 0.4;
//    	double curve = 0.0;    	
//    	switch(autoMode) {
//    	case "L":
//    		arcadeDrive.arcadeDrive(0.1, 0);
//    		Timer.delay(4); // wait for a motor update time
//    		break;
//    	case "M":
//    		//driveTrain.drive(autoPower, curve);
//    		Timer.delay(4);		// wait for a motor update time
//    		break;
//    	case "R":
//    		//driveTrain.drive(autoPower, curve);
//    		Timer.delay(4);		// wait for a motor update time
//    	default:
//    		Timer.delay(0.05);		// wait for a motor update time
//    		break;
//    	}
    }

    /**
     * TELEOPERATED MODE. Runs the motors with arcade steering.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	arcadeDrive.arcadeDrive(-stick.getY(), -stick.getX());
        	
        	//testing servos
        	Servo exampleServo = new Servo(1);
        	
        	exampleServo.set(.5);;
        	exampleServo.setAngle(75);
    		
        	/*
        	x = stick.getX();
        	y = stick.getY();
        	expX = 0;
        	expY = 0;
        	expX = Math.abs(x) > DEADZONEX ? x * Math.abs(x) : 0;
        	expY = Math.abs(y) > DEADZONEY ? y * Math.abs(y) : 0;
        	expX = Math.abs(x) > DEADZONEX ? Math.pow(x, 3) : 0;
        	expY = Math.abs(y) > DEADZONEY ? Math.pow(y, 3) : 0;
        	bLeft.set(expX - expY);
    		fLeft.set(expX - expY);
    		bRight.set(expX + expY);
    		fRight.set(expX + expY);
    		*/
        	
        	
        	/**
        	 * MECHANISMS
        	 */
        	/*
        	boolean openMechDoor = stick.getRawButton(MECH_DOOR_OPEN);
        	boolean closeMechDoor = stick.getRawButton(MECH_DOOR_CLOSE);
        	boolean climbUpRope = stick.getRawButton(CLIMBING_MECH_UP);
        	boolean climbDownRope = stick.getRawButton(CLIMBING_MECH_DOWN);
        	 */
        	
        	//gears mech
    		/*if (openMechDoor)
    			gearMechDoor.set(0.2); //don't know what to set this to
    		} 
    		if (closeMechDoor) {
    			gearMechDoor.set(-0.2); //don't know what to set this to
    		} */
        	
        	
        	//climbing mech
        	/*if (climbUpRope)
				climbingMech.set(0.2); //don't know what to set this to
			} 
			if (climbDownRope) {
				climbingMech.set(-0.2); //don't know what to set this to
			} */
        	
        	
    		
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
    	fLeft.set(0);
    	fRight.set(0);
    	bLeft.set(0);
    	bRight.set(0);
    	//mechDoor.set(0);
    	//climbingMech.set(0);
    	System.out.println("I prefer differently abled you ableist");
    }
    
    /*
     * fLeft.set(stick.getX() - (stick.getY() * 0.5));
		bLeft.set(stick.getX() - (stick.getY() * 0.5));
		fRight.set(stick.getX() + (stick.getY() * 0.5));
		bRight.set(stick.getX() + (stick.getY() * 0.5));
     */
}
