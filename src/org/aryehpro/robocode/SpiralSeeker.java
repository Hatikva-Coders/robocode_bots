package org.aryehpro.robocode;

import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * Created by Aryeh on 29/12/2015.
 */
public class SpiralSeeker extends Robot {

    @Override
    public void run() {

        init();

        seek();

        while (true) {

            if (_timeSinceScanEvent == MAX_NO_SCAN) {
                _haveTarget = false;
                seek();
            }

            if (_targetDistance <= CIRCILING_RADIUS) {
                setDirection(_targetAngle - 90);
            } else {
                setDirection(_targetAngle - 90 + _direction * 20);
            }

            handleShot();

            _timeSinceScanEvent++;

            correctRadar();
            scan();

            ahead(_direction * DISTANCE_PER_TURN);

        }

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {

        _timeSinceScanEvent = 0;

        if (_scanSleep == 0) {
            System.out.println("scan event, target status: " + (_haveTarget ? "have" : "don't have"));

            if (checkTargetName(event.getName())) {
                lockRadarToTarget(event);
                computeTargetLocation(event);
            }

            _scanSleep = SLEEP_PERIOD;
        } else {
            _scanSleep--;
        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {

        _direction *= -1;

    }

    //region Private Methods

    private void init() {
        _targetName = "";
        _targetAngle = -1;
        _targetDistance = -1;
        _haveTarget = false;
        _scanSleep = 0;
        _direction = 1;
        _lastTurnDirection = 1;
        setAdjustRadarForRobotTurn(true);
        setAdjustGunForRobotTurn(true);
    }

    private void seek() {

        System.out.println("seeking");

        while (!_haveTarget) {
            turnRadarRight(45 * _lastTurnDirection);
        }
    }

    private void correctRadar() {

        if (_haveTarget) {
            turnRadarRight(_direction * (Math.toDegrees(Math.asin(DISTANCE_PER_TURN / _targetDistance)) + 180));
        }

    }

    private void lockRadarToTarget(ScannedRobotEvent event) {
        double radarTurn = getRealAngle(getHeading() + event.getBearing() - getRadarHeading());
        _lastTurnDirection = (radarTurn > 0 ? 1 : -1);
        turnRadarRight(radarTurn);
    }

    private void computeTargetLocation(ScannedRobotEvent event) {

        _targetDistance = event.getDistance();
        _targetAngle = event.getBearing() + getHeading();

    }

    private void handleShot() {

        if (_haveTarget && _targetDistance < MAX_FIRING_RANGE) {
            setGunAngle(_targetAngle);
            fire(BASE_FIRE_POWER + ((MAX_FIRING_RANGE - _targetDistance) * RANGE_POWER_MULTIPLIER));
        }

    }

    private void setDirection(double requestedAngle) {

        turnRight(requestedAngle - this.getHeading());


    }

    private void setGunAngle(double requestedAngle) {

        turnGunRight(requestedAngle - this.getGunHeading());

    }

    private boolean checkTargetName(String name) {
        if (!_haveTarget) {
            _targetName = name;
            _haveTarget = true;

            return true;
        }

        if (_targetName.equals(name)) {
            return true;
        }

        return false;
    }

    private static double getRealAngle(double degrees) {

        if (Math.abs(degrees) > 360) {
            return degrees % 360;
        }

        return degrees;

    }

    //endregion

    //region Fields

    private String _targetName;
    private double _targetDistance;
    private double _targetAngle;
    private boolean _haveTarget;
    private int _scanSleep;
    private int _timeSinceScanEvent;
    private int _direction;
    private int _lastTurnDirection;

    //endregion

    //region Finals

    private final double MAX_FIRING_RANGE = 300;
    private final double CIRCILING_RADIUS = 100;
    private final double DISTANCE_PER_TURN = 35;
    private final double BASE_FIRE_POWER = 1.5;
    private final double RANGE_POWER_MULTIPLIER = 0.0075;
    private final int SLEEP_PERIOD = 1;
    private final int MAX_NO_SCAN = 1;

    //endregion

}
