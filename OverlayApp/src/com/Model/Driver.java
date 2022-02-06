package com.Model;

import com.Overlay.DriverLabel;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class Driver implements Comparable<Driver> {
    private String name;
    private final int driverID;
    private int currentPos;
    private int startingPos;
    private int posDiff;
    private int qualPos;
    private String qualTime;
    private String fastestLap;
    private String car;

    private final ImageIcon posBanner = new ImageIcon("assets/NameBannerPos.png");

    /**
     * Constructor
     * @param driverID Driver ID from AC
     * @param name Driver name from AC
     */
    public Driver(int driverID, String name) {
        this.driverID = driverID;
        this.name = name;
        this.currentPos = 0;
        this.startingPos = 0;
        this.posDiff = 0;
        this.qualTime = "";
        this.fastestLap = "";
        this.car = "";
        this.qualPos = 0;
    }

    /**
     * get drivers qualifying position
     * @return integer of position
     */
    public int getQualPos() {
        return qualPos;
    }

    /**
     * Set drivers qualifying position
     * @param qualPos integer for position
     */
    public void setQualPos(int qualPos) {
        this.qualPos = qualPos;
    }

    /**
     * Get Drivers name
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Set drivers name
     * @param name string
     */
    public void setName(String name) {
        this.name = name;
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
     * Get drivers qualifying time
     * @return String
     */
    public String getQualTime() {
        return qualTime;
    }

    /**
     * Set drivers qualifying time
     * @param qualTime String
     */
    public void setQualTime(String qualTime) {
        this.qualTime = qualTime;
    }

    /**
     * Get drivers fastest Lap
     * @return String
     */
    public String getFastestLap() {
        return fastestLap;
    }

    /**
     * Set drivers fastest Lap
     * @param fastestLap String
     */
    public void setFastestLap(String fastestLap) {
        this.fastestLap = fastestLap;
    }

    /**
     * Get drivers car
     * @return String
     */
    public String getCar() {
        return car;
    }

    /**
     * Set Drivers car
     * @param car string
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     * Get name JLabel
     * @return JLabel
     */
    private JLabel getDriverLabel() {
        DriverLabel driverLabel = new DriverLabel(name);

        return driverLabel;
    }

    /**
     * Get Position label
     * @return JLabel
     */
    private JLabel getPositionLabel() {
        JLabel positionLabel = new JLabel(String.valueOf(currentPos));
        positionLabel.setForeground(Color.WHITE);
        positionLabel.setIcon(posBanner);
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        positionLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        return positionLabel;
    }

    /**
     * Get completed box for overlay panel
     * @return Box
     */
    public Box getBox() {
        Box driverBox = Box.createHorizontalBox();
        driverBox.add(getPositionLabel());
        driverBox.add(getDriverLabel());

        return driverBox;
    }

    /**
     * Get position difference
     */
    public int getPosDiff() {
        posDiff = Math.abs(startingPos - currentPos);

        return posDiff;
    }

    @Override
    public int compareTo(Driver o) {
        return 0;
    }

    public static class Comparators {
        public static Comparator<Driver> currentPos = new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                return o1.getCurrentPos() - o2.getCurrentPos();
            }
        };
    }
}
