package org.aryehpro.lesson3Examples.style;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class TabsGood extends Robot {

    @Override
    public void run() {

        int outer, inner;
        outer = 0;
        inner = 0;

        boolean keepGoing = true;

        while (outer < 4 && keepGoing) {

            while (inner < 4 && keepGoing) {

                System.out.println("outer * inner is: " + outer * inner);
                inner++;
                
                if (inner == 1) {
                    keepGoing = false;
                }

            }

            outer++;

        }

    }
}
