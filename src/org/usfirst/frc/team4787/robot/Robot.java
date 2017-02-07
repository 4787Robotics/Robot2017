
package org.usfirst.frc.team4787.robot;

//2017

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Talon;
import java.util.Random;



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
	RobotDrive myDrive;
	
	
	//final double DEADZONEX = 0.07, DEADZONEY = 0.07;
   // final int MECH_DOOR_OPEN = 5, MECH_DOOR_CLOSE = 3;
    
	

	
    Joystick stick;

    Talon bLeft = new Talon(1);
    Talon fLeft = new Talon(2);
    Talon bRight= new Talon(3);
    Talon fRight = new Talon(4);
   // Jaguar mechDoor = new Jaguar(9);
    
    double y, x;
    double expY, expX;
    //int signY, signX;
  
    
    public void robotInit() {
    	myDrive = new RobotDrive(1, 2, 3, 4);
    	stick = new Joystick(0);
    }
    

    /**
     * Move autonomously....
     */
    public void autonomous() {
    	Random rand = new Random();
    	double randLeft = rand.nextDouble() * 0.1 - rand.nextDouble() * 0.1;
    	double randRight = rand.nextDouble() * 0.1 - rand.nextDouble() * 0.1;
    	
    	fLeft.set(randLeft);
		bLeft.set(randLeft); 
		fRight.set(randRight);
		bRight.set(randRight);
		
		 Timer.delay(0.05);		// wait for a motor update time
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	//myDrive.arcadeDrive(stick);
        	myDrive.arcadeDrive(-stick.getY(), -stick.getX());
        	
        	
        	
        	/*
        	y = stick.getY();
        	x = stick.getX();
        	//signY = (int)Math.signum(y);
        	//signX = (int)Math.signum(x);
        	 */
        
        	//boolean openMechDoor = stick.getRawButton(MECH_DOOR_OPEN);
        	//boolean closeMechDoor = stick.getRawButton(MECH_DOOR_CLOSE);

        	// Determines if joystick is out of deadzone
        	/*
    		if(x>DEADZONEX || x<-DEADZONEX){
    			expX = Math.pow(x, 3); // x^3 for nonlinear control
    			
    		}
    		if(y>DEADZONEY || y<-DEADZONEY){
    			expY = Math.pow(y, 3); // y^3 for nonlinear control
    		}
        	
        	expX = 0;
            expY = 0;
            if (Math.abs(x) > DEADZONEX) {
                expX = x * Math.abs(x); //squares x while maintaining the original sign of x
            	//expX = x;
            } else {
                expX = 0;
            }
            if (Math.abs(y) > DEADZONEY) {
               //expY = y;
            	expY = y * Math.abs(y);
            } else {
                expY = 0;
            }
    		*/

    		/*
    	    // Motor power settings
    		fLeft.set(expX - expY);
    		bLeft.set(expX - expY);
    		fRight.set(expX + expY);
    		bRight.set(expX + expY);
            */
            
    		/*fLeft.set(expY);
    		bLeft.set(expY);
    		fRight.set(expX);
    		bRight.set(expX);
    		 */
            
    		//System.out.println(fLeft);
    		//System.out.println(fRight);
    		//System.out.println(bLeft);
    		//System.out.println(bRight);
    		//System.out.println("Left motors set to: " + (expY));
    		//System.out.println("Right motors set to: " + (expX));
    		
    		/*
    		fLeft.set(x - y);
    		bLeft.set(x - y);
    		fRight.set(x + y);
    		bRight.set(x + y);
    		*/
    		
    		/*if (openMechDoor) y
    			mechDoor.set(0.2); //don't know what to set this to
    		} 
    		if (closeMechDoor) {
    			mechDoor.set(-0.2); //don't know what to set this to
    		} */
    		
    	    Timer.delay(0.005);		// wait for a motor update time
    	   // System.out.println("X: " + expX + "  Y: " + expY);
        }
    }

    /**
     * Runs during test modes
     */
    public void test() {
    	//rawr
    	System.out.println("test function");
    }
    
    public void disabled()
    {
    	fLeft.set(0);
    	fRight.set(0);
    	bLeft.set(0);
    	bRight.set(0);
    	//mechDoor.set(0);
    	System.out.println("I prefer differently abled you ableist");
    }
    
    /*
     * fLeft.set(stick.getX() - (stick.getY() * 0.5));
		bLeft.set(stick.getX() - (stick.getY() * 0.5));
		fRight.set(stick.getX() + (stick.getY() * 0.5));
		bRight.set(stick.getX() + (stick.getY() * 0.5));
     */
    
  
}
