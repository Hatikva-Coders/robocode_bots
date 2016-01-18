package org.aryehpro.lesson3Examples.style;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class LineBreaksGood extends Robot {

    @Override
    public void run() {

        int number;
        number = 5;

        char letter;
        letter = 'b';

        number = number * number * number;
        letter = 'c';

        System.out.println("number is: " + number);
        System.out.println("letter is: " + letter);

        boolean doLoop = true;

        while (doLoop) {

            if (number == 9) {
                doLoop = false;
            }

            System.out.println("doLoop is still true");
            number++;

        }

    }
}
