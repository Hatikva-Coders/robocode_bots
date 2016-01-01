package org.aryehpro.robocode;

import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Created by Aryeh on 19/12/2015.
 */
public class RadarExperiment extends Robot {

    @Override
    public void run() {

        turnRadarRight(Double.POSITIVE_INFINITY);

        do {
            scan();
        } while (true);

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        double radarTurn = getRealAngle(getHeading() + event.getBearing() - getRadarHeading());
        turnRadarRight(radarTurn);

    }

    private static double getRealAngle(double degrees) {

        if (Math.abs(degrees) > 360) {
            return degrees % 360;
        }

        return degrees;

    }
}
