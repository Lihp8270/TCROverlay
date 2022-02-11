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
    private int qualTime;
    private int fastestLap;
    private int currentLap;
    private String qualTimeString;
    private String currentLapString;
    private String fastestLapString;
    private String car;
    private final ImageIcon posBanner;
    private final Config config;
    private String changeDir;

    /**
     * Constructor
     * @param driverID Driver ID from AC
     * @param name Driver name from AC
     */
    public Driver(int driverID, String name, Config config) {
        this.driverID = driverID;
        this.name = name;
        this.currentPos = 0;
        this.startingPos = 0;
        this.posDiff = 0;
        this.qualTime = 0;
        this.fastestLap = 0;
        this.car = "";
        this.qualPos = 0;
        this.config = config;
        this.posBanner = new ImageIcon(this.config.getPositionIcon());
        this.changeDir = "";
        this.currentLap = 0;
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
     * @return int
     */
    public int getQualTime() {
        return qualTime;
    }

    /**
     * Set drivers qualifying time
     * @param qualTime Integer
     */
    public void setQualTime(int qualTime) {
        this.qualTime = qualTime;
    }

    /**
     * Get drivers fastest Lap
     * @return Int
     */
    public int getFastestLap() {
        return fastestLap;
    }

    /**
     * Set drivers fastest Lap
     * @param fastestLap String
     */
    public void setFastestLap(int fastestLap) {
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
        String banner;

        if (changeDir.equals("+")) {
            banner = config.getNameBannerIconUp();
        } else if (changeDir.equals("-")) {
            banner = config.getNameBannerIconDown();
        } else {
            banner = config.getNameBannerIconSteady();
        }

        DriverLabel driverLabel = new DriverLabel(name, banner);

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
     * Get position change label
     * @return JLabel
     */
    private JLabel getPosChangeLabel() {
        String change;
        String banner;

        if (changeDir.equals("+")) {
            change = String.valueOf(posDiff);
            banner = config.getPosChangeIconUp();
        } else if (changeDir.equals("-")) {
            change = String.valueOf(posDiff);
            banner = config.getPosChangeIconDown();
        } else {
            change = "";
            banner = config.getPosChangeIconSteady();
        }

        JLabel posChangeLabel = new JLabel(change);
        ImageIcon icon = new ImageIcon(banner);
        posChangeLabel.setForeground(Color.WHITE);
        posChangeLabel.setIcon(icon);
        posChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        posChangeLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        return posChangeLabel;
    }

    /**
     * Get completed box for overlay panel
     * @return Box
     */
    public Box getBox() {
        Box driverBox = Box.createHorizontalBox();
        driverBox.add(getPositionLabel());
        driverBox.add(getDriverLabel());
        driverBox.add(getPosChangeLabel());

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
     * + = Improving
     * - = Falling back
     * "" = Steady
     * @return + - or empty
     */
    public String getChangeDir() {
        return changeDir;
    }

    /**
     * Get position difference
     * @return position difference
     */
    public int getPosDiff() {
        return posDiff;
    }

    /**
     * Get current lap as integer
     * @return lap ms
     */
    public int getCurrentLap() {
        return currentLap;
    }

    /**
     * Set lap time integer
     * @param currentLap ms
     */
    public void setCurrentLap(int currentLap) {
        this.currentLap = currentLap;
    }

    /**
     * Get qualifying time as a string
     * @return x:xx.xxx
     */
    public String getQualTimeString() {
        return qualTimeString;
    }


    /**
     * Get current Lap as String
     * @return x:xx.xxx
     */
    public String getCurrentLapString() {
        return currentLapString;
    }

    /**
     * Get fastest lap as a string
     * @return x:xx.xxx
     */
    public String getFastestLapString() {
        return fastestLapString;
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
