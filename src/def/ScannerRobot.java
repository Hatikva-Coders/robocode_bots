package def;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;

/**
 * A robot to be sub-classed by students for use in ex2 part 3. This robot holds a boolean field (robotSeen) that
 * indicates if the robot's radar is currently aimed at another robot.
 *
 * edit 18/01/2016 (achi):
 * 		added the member currentGunHeat, that encapsulates the need to call getGunHeat().
 * 		for use in ex3.
 * 
 * Created by Aryeh on 13/01/2016.
 */
public class ScannerRobot extends Robot{

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        lastEventTime = getTime();
        robotSeen = true;

    }

    @Override
    public void onStatus(StatusEvent e) {
        if(e.getTime() - lastEventTime > 5) {
            robotSeen = false;
        }
		
		currentGunHeat = getGunHeat();
    }

	protected double currentGunHeat;
    protected boolean robotSeen;
    private long lastEventTime;

}
