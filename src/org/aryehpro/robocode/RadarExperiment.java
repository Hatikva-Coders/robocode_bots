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
        }while (true);

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        double radarTurn = getHeading() + event.getBearing() - getRadarHeading();
        turnRadarRight(radarTurn);

    }
}
