package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class NestedLoop extends Robot {

    @Override
    public void run() {

        int outer, inner;
        outer = 0;
        inner = 0;

        while (outer < 3) {

            while (inner < 3) {

                System.out.println("outer * inner is: " + outer*inner);
                inner++;

            }

            outer++;

        }

    }
}
