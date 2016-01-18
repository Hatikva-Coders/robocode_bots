package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 19/01/2016.
 */
public class IfElseCode extends Robot {

    @Override
    public void run() {

        char lastLetterInName = 'h';
        String name;

        if (lastLetterInName == 'r') {
            name = "Achi-Or";
        } else {
            name = "Aryeh";
        }

        System.out.println("your name must be " + name);

    }
}
