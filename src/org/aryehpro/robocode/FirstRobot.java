package org.aryehpro.robocode;

import robocode.*;
import robocode.Robot;

import java.awt.*;

/**
 * Created by Aryeh on 16/12/2015.
 */
public class FirstRobot extends Robot {

    private static final double DEF_POWER = Rules.MIN_BULLET_POWER + (Rules.MAX_BULLET_POWER - Rules.MIN_BULLET_POWER) / 2;
    ;
    private static final int DEF_TTL = 3;

    @Override
    public void run() {

        init();

        while (true) {

            turnRight(4 * _direction);
            ahead(4);

            if (getGunHeat() == 0 && _seenRobot) {

                shootSequence();

            }

            _seenRobot = false;

        }

    }

    private void shootSequence() {
        double power = DEF_POWER;

        if (_seenDistance < 300) {
            power += 1;
        }

        fire(power);

        if (_seenDistance < 200) {
            fire(power);
        }
    }

    private void init() {
        setScanColor(Color.ORANGE);
        _seenRobot = false;
        _direction = 1;
        _directionTTL = DEF_TTL;
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        if (!_seenRobot) {
            _seenRobot = true;
            _seenDistance = event.getDistance();

            if (_directionTTL == 0 || _seenDistance > 200) {
                _direction *= -1;
                _directionTTL = DEF_TTL;
            } else {
                _directionTTL--;
            }

        }
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        super.onHitByBullet(event);
    }

    private boolean _seenRobot;
    private double _seenDistance;
    private int _direction;
    private int _directionTTL;

}