package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class ComplexLoop extends Robot {

    @Override
    public void run() {

        int grower, shrinker;
        grower = 0;
        shrinker = 10;

        while (grower < 5 || shrinker > 8) {
            System.out.println("still in yr loopz!");
            grower++;
            shrinker--;
        }

    }
}
