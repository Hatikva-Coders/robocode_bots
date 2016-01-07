package org.achi.robocode;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * AchiRobot - a robot by Achi
 */
public class AchiRobot extends AdvancedRobot
{
	private long last_eye_contact;
	private final long RESCAN_TIME = 80;
	private double moving_direction;
	private double fire_power = 1;

	/**
	 * run: AchiRobot's default behavior
	 */
	public void run() {
		last_eye_contact = getTime() - RESCAN_TIME*2;
		long now;
		int direction = 1;
		// Initialization of the robot should be put here
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		setColors(Color.red,Color.green,Color.green); // body,gun,radar

		// Robot main loop
		while ( true ){
			ahead( 100*direction );
			direction = choose_direction(direction);
			now = getTime();
			if (   now - this.last_eye_contact < RESCAN_TIME ){  // short time
				maintain_eye_contact();
			}
			else { //long time no see
				wide_scan();
			}
		}

	}

	private int direction_counter = 0;
	private int choose_direction(int last_direction){
		direction_counter++;
		if ( direction_counter%1==0 )
			return last_direction*-1;
		else return last_direction;
	}

	/*
	 * This method assumes we lately had the enemy-robot in our radar
	 * It will jaja a little in order to maintain lock on target.
	 */
	private void maintain_eye_contact(){
		//System.out.println("maintain: Heading=" + getHeading() + "");
		turnRadarRight(10);
		turnRadarRight(-10);
	}

	/*
	 * This method assumes we lost track of the enemy robot
	 * Therefor it will do a wide scan to find it.
	 */
	private void wide_scan(){
		turnRadarRight(360);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
		setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
		last_eye_contact = getTime();

		fire(fire_power);
		//double distance = e.getDistance();
		//double bearing = e.getBearing();
		System.out.println("onScannedRobot. fire_power=" + fire_power);

	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		double bullet_heading = e.getHeading();
		//	understand where the bullet came from
		System.out.println( "HitByBullet Heading " + bullet_heading );
		// 	rotate to be perpendicular to it
		if ( Math.abs( (bullet_heading+90) - this.getHeading() ) < Math.abs( (bullet_heading-90) - this.getHeading() ) ) {
			moving_direction = bullet_heading + 90;
		}
		else {
			moving_direction = bullet_heading - 90;
		}
		System.out.println("HitByBullet. move by: " + ( moving_direction - this.getHeading() ) );
		turnRight( moving_direction - this.getHeading() );

		ahead(20);
		// 	move using long moves (faster than short ones.) - in the main loop

	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// the wall with respect to where i am heading -180 to +180
		double bearing = e.getBearing();
		int new_direction;
		if ( Math.abs(bearing) > 90 ) {
			new_direction = 1;
		}
		else {
			new_direction = -1;
		}
		ahead(new_direction*20);
		//turnRight( 2*bearing*new_direction );
		System.out.println("onHitWall, bearing = " + bearing);
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		System.out.println("OnBulletHit");
		fire_power *= 1.1;
		super.onBulletHit(event);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		fire_power *= 0.98;
		super.onBulletMissed(event);
	}
}
