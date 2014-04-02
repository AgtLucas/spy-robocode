/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nsa;

import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.BulletMissedEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

/**
 *
 * @author AgtLucas
 */
public class Spy extends AdvancedRobot {
 
    double previousEnergy = 100;
    int movementDirection = 1;
    int gunDirection = 1;
    private int wallMargin = 60; 
    
    public void run() {
        // Set colors
        setBodyColor(Color.black);
        setGunColor(Color.black);
        setRadarColor(Color.black);
        setBulletColor(Color.black);
        setScanColor(Color.black);
        
        setTurnGunRight(99999);
    }
    
    public void onScannedRobot(
        ScannedRobotEvent e) {
          // Stay at right angles to the opponent
        setTurnRight(e.getBearing()+90-30*movementDirection);

         // If the bot has small energy drop,
        // assume it fired
        double changeInEnergy = previousEnergy-e.getEnergy();
        if (changeInEnergy>0 &&
            changeInEnergy<=3) {
             // Dodge!
             movementDirection =
              -movementDirection;
             setAhead((e.getDistance()/4+25)+movementDirection);
         }
        // When a bot is spotted,
        // sweep the gun and radar
        gunDirection = -gunDirection;
        setTurnGunRight(99999*gunDirection);

        // Fire directly at target
        fire ( 2 ) ;

        // Track the energy level
        previousEnergy = e.getEnergy();
    }
    
    public void onRobotDeath(RobotDeathEvent e) {
        e.getName();
    }
    
    public void onBulletMissed(BulletMissedEvent e) {
        e.getBullet();
    }
    
}
