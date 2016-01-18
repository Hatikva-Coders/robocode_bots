package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class LoopCode extends Robot {

    @Override
    public void run() {

        int numberOfLoops = 10;
        int currentLoop = 0;
        int bigNumber = 2;

        while (currentLoop < numberOfLoops) {

            System.out.println("bigNumber is now: " + bigNumber);
            bigNumber = bigNumber * 2;

            currentLoop++;

        }

    }
}
