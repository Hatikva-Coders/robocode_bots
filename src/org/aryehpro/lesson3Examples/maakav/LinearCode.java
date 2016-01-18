package org.aryehpro.lesson3Examples.maakav;

import robocode.Robot;

/**
 * Created by Aryeh on 18/01/2016.
 */
public class LinearCode extends Robot {

    @Override
    public void run() {

        int firstNumber, secondNumber;

        firstNumber = 4;
        secondNumber = firstNumber * firstNumber;
        firstNumber = 6;
        secondNumber = 5;
        firstNumber = firstNumber + secondNumber;

        int thirdNumber = 3;

        secondNumber = firstNumber * thirdNumber;

        System.out.println("The value of firstNumber is: " + firstNumber);
        System.out.println("The value of secondNumber is: " + secondNumber);
        System.out.println("The value of thirdNumber is: " + thirdNumber);

    }
}
