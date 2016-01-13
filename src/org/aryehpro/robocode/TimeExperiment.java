package org.aryehpro.robocode;

import robocode.Robot;

/**
 * Created by Aryeh on 13/01/2016.
 */
public class TimeExperiment extends Robot {

    @Override
    public void run() {

        while (getTime() < 500) {
            ahead(1);
        }

    }

}
