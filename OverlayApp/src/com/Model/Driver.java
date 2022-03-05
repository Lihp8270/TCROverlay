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
     * @return Box
     */
    public Box getBox(String focussedDriver) {
        Box driverBox = Box.createHorizontalBox();

        if (focussedDriver.equals(name)) {
            driverBox.add(tStackEngine.getFocussedDriverLabel());
        } else {
            driverBox.add(tStackEngine.getPositionLabel(String.valueOf(currentPos)));
        }

        driverBox.add(tStackEngine.getDriverLabel(name));
        driverBox.add(tStackEngine.getLapsLabel("Laps : " + completedLaps));
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

    @Override
    public int compareTo(Driver o) {
        return 0;
    }

    public static class Comparators {
        public static Comparator<Driver> currentPos = Comparator.comparingInt(Driver::getCurrentPos);
    }
}
