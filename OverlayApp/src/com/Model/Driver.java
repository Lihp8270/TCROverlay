package com.Model;

import com.Engine.TimingStackLabelEngine;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Comparator;

public class Driver implements Comparable<Driver> {
    private final String name;
    private final int driverID;
    private String car;
    private int currentPos;
    private int startingPos;
    private int posDiff;
    private int completedLaps;
    private String changeDir;
    private final TimingStackLabelEngine tStackEngine;
    private String focussedDriver;
    private String delta;
    private int onTrack;

    /**
     * Constructor
     * @param driverID Driver ID from AC
     * @param name Driver name from AC
     */
    public Driver(int driverID, String name, Config config) throws IOException, FontFormatException {
        this.driverID = driverID;
        this.name = name;
        this.currentPos = 0;
        this.startingPos = 0;
        this.posDiff = 0;
        this.changeDir = "";
        this.completedLaps = 0;
        this.tStackEngine = new TimingStackLabelEngine(config);
        this.car = "";
        this.delta = "+0.000";
        this.onTrack = 1;
    }

    /**
     * Get Drivers name
     * @return string
     */
    public String getName() {
        return name;
    }


    /**
     * Get drivers ID
     * @return integer
     */
    public int getDriverID() {
        return driverID;
    }

    /**
     * Get drivers current position
     * @return integer
     */
    public int getCurrentPos() {
        return currentPos;
    }

    /**
     * Set drivers current position
     * @param currentPos integer
     */
    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * Get drivers starting position
     * @return integer
     */
    public int getStartingPos() {
        return startingPos;
    }

    /**
     * Set drivers starting position
     * @param startingPos integer
     */
    public void setStartingPos(int startingPos) {
        this.startingPos = startingPos;
    }

    /**
     * Get completed box for overlay panel
     * @param focussedDriver Name of focussed Driver
     * @param leaderLapCount Number of laps completed by leader
     * @param carAheadDelta Delta of the car ahead
     * @param mode 0 = delta to lead, 1 = delta to car ahead
     * @return Box
     */
    public Box getBox(String focussedDriver, int leaderLapCount, double carAheadDelta, int mode) {
        Box driverBox = Box.createHorizontalBox();
        double deltaToCarAhead = Double.parseDouble(delta) - carAheadDelta;
        int lapDifference = leaderLapCount - completedLaps;

        if (focussedDriver.equals(name)) {
            driverBox.add(tStackEngine.getFocussedDriverLabel());
        } else {
            driverBox.add(tStackEngine.getPositionLabel(String.valueOf(currentPos)));
        }

        driverBox.add(tStackEngine.getDriverLabel(name));
        switch (onTrack) {
            case 0:
                driverBox.add(tStackEngine.getLapsLabel("IN PIT"));
                break;
            default:
                if(currentPos == 1) {
                    driverBox.add(tStackEngine.getLapsLabel("Laps : " + completedLaps));
                } else if(lapDifference > 1) {
                    if (lapDifference - 1 == 1) {
                        driverBox.add(tStackEngine.getLapsLabel("+" + String.valueOf(lapDifference - 1) + " Lap"));
                    } else {
                        driverBox.add(tStackEngine.getLapsLabel("+" + String.valueOf(lapDifference - 1) + " Laps"));
                    }
                } else {
                    switch (mode) {
                        // Delta to lead
                        case 0:
                            driverBox.add(tStackEngine.getLapsLabel("+" + delta));
                            break;
                        // Delta to car ahead
                        default:
                            if (deltaToCarAhead < 0) {
                                driverBox.add(tStackEngine.getLapsLabel("OVERTAKE"));
                            } else {
                                driverBox.add(tStackEngine.getLapsLabel("+" + String.format("%.3f", deltaToCarAhead)));
                            }
                            break;
                    }
                }
                break;
        }


        driverBox.add(tStackEngine.getPositionChangeLabel(changeDir,String.valueOf(posDiff)));

        return driverBox;
    }

    /**
     * Calculate Position difference
     */
    public void setPosDiff() {
        posDiff = Math.abs(startingPos - currentPos);
    }

    /**
     * Set change direction
     */
    public void setChangeDir() {
        if(getCurrentPos() < getStartingPos()) {
            changeDir = "+";
        } else if(getCurrentPos() > getStartingPos()) {
            changeDir = "-";
        } else {
            changeDir = "";
        }
    }

    /**
     * Set number of completed laps
     * @param completedLaps Integer
     */
    public void setCompletedLaps(int completedLaps) {
        this.completedLaps = completedLaps;
    }

    /**
     * Get completed laps
     * @return integer
     */
    public int getCompletedLaps() {
        return completedLaps;
    }

    /**
     * Get position difference
     * @return position difference
     */
    public int getPosDiff() {
        return posDiff;
    }

    /**
     * Set the focussed driver for camera
     * @param focussedDriver Driver name
     */
    public void setFocussedDriver(String focussedDriver) {
        this.focussedDriver = focussedDriver;
    }

    /**
     * Accessor for focussed driver
     * @return String
     */
    public String getFocussedDriver() {
        return focussedDriver;
    }

    /**
     * Get a car
     * @return
     */
    public String getCar() {
        return car;
    }

    /**
     * Set the car
     * @param car
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     * Set delta to race leader
     * @param delta #.###
     */
    public void setDelta(String delta) {
        this.delta = delta;
    }

    /**
     * Accessor for delta to race leader
     * @return +#.###
     */
    public String getDelta() {
        return delta;
    }

    /**
     * 1 = on track
     * 0 = in pit
     * @param status int 1 or 0
     */
    public void setOnTrack(int status) {
        this.onTrack = status;
    }

    public int getOnTrack() {
        return onTrack;
    }

    @Override
    public int compareTo(Driver o) {
        return 0;
    }


    public static class Comparators {
        public static Comparator<Driver> currentPos = Comparator.comparingInt(Driver::getCurrentPos);
    }
}
