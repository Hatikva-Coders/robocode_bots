package org.aryehpro.lesson3Examples.style;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class LineBreaksNotGood extends Robot {

    @Override
    public void run() {
        int a; char b;
        a = 5; b = 'b';
        a = a * a * a; b = 'c';
        System.out.println("a is: " + a);System.out.println("b is: " + b);
        boolean doLoop = true;
        while (doLoop){ if (a == 8) { doLoop = false;
            }
            System.out.println("doLoop is still true");
            a++;
        }
    }
}
