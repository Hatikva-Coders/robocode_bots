package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class ComplexIfLoop extends Robot {

    @Override
    public void run() {

        boolean keepGoing = true;
        int numberOfLoops = 0;

        while (numberOfLoops < 10 && keepGoing) {

            System.out.println("loop-di-doo!");

            if (numberOfLoops % 7 == 0) {
                keepGoing = false;
            }

        }

        System.out.println("numberOfLoops is: " + numberOfLoops);

    }
}
