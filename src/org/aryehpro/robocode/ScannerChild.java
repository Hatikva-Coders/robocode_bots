package org.aryehpro.robocode;

import def.ScannerRobot;

/**
 * Created by Aryeh on 13/01/2016.
 */
public class ScannerChild extends ScannerRobot {

    @Override
    public void run() {
        while (true) {

            turnGunRight(1);
            if(robotSeen) {
                ahead(4);
            }

        }
    }
}
