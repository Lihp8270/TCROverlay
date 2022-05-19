package com.Model.Overlay;

import com.Model.Config;
import com.Model.Driver;
import com.Model.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DriversPanel extends InitPanel {
    ArrayList<Driver> drivers;
    private JLabel timingTreeHeader;
    private Box headerBox;
    private int maxDrivers;
    private Config config;
    private Boolean raceStarted;

    public DriversPanel(Config config) {
        super();
        drivers = new ArrayList<>();
        headerBox = Box.createHorizontalBox();
        timingTreeHeader = new JLabel();
        timingTreeHeader.setIcon(new ImageIcon(config.getTimingTreeHeader()));
        timingTreeHeader.setVisible(true);
        headerBox.add(timingTreeHeader);
        this.config = config;
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(config.getDriverListTopPadding(), config.getDriverListLeftPadding(),0,0));
        this.maxDrivers = config.getMaxDriversDisplay();
        this.raceStarted = false;
    }

    /**
     * Build and return drivers panel
     * @param focussedDriver Name of focussed Driver
     * @param mode 0 = delta to leader, 1 = delta to car ahead
     * @return Returns completed Drivers panel
     */
    public JPanel getPanel(String focussedDriver, int mode, TopPanel lapPanel, Session currentSession) {
        int driverCount = 1;
        double carAheadDelta = 0.000;

        // TODO posDiff stays 0 through P and Q
        // TODO startingPos is not set during P and Q

        // Remove spectator cars, sort them, then set position numbers without spec cars
        hideSpectatorCars();
        Collections.sort(drivers, Driver.Comparators.currentPos);
        resetPositions();
        raceStarted = false;

        // If not a race session start immediately (ie. if qualifying or practice)
        // If it is a Race session, check to see if anybody has started
        if (!currentSession.getSessionID().equals("R")) {
            raceStarted = true;
        } else {
            for (Driver driver : drivers) {
                if (driver.getRaceStarted() == 1) {
                    raceStarted = true;
                }
            }
        }


        this.panel.add(headerBox);
        this.panel.add(Box.createRigidArea(new Dimension(0,3)));
        for (Driver driver : drivers) {
            if (!driver.isSpectator()) {
                this.panel.add(driver.getBox(focussedDriver, drivers.get(0).getCompletedLaps(), carAheadDelta, mode, lapPanel.getMaxLaps()));
                carAheadDelta = Double.parseDouble(driver.getDelta());
                this.panel.add(Box.createRigidArea(new Dimension(0,3)));
                if (driverCount == maxDrivers) {
                    break;
                }
                driverCount++;
            }

        }

        return this.panel;
    }

    private void resetPositions() {
        int pos = 1;

        for (Driver driver : drivers) {
            if (driver.isSpectator()) {
                driver.setCurrentPos(99);
            } else {
                driver.setCurrentPos(pos);
                pos++;
            }
        }
    }

    private void hideSpectatorCars() {
        for (String spectator : config.getSpectatorCars()) {
            for (int i = 0; i < drivers.size(); i++) {
                if (spectator.equals(drivers.get(i).getName())) {
                    drivers.get(i).setSpectator(true);
                }
            }

        }
    }

    /**
     * Add driver to the driver list
     * @param driver completed driver box containing position, name bar and position change labels
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    /**t
     * Accessor for driver array list
     * @return ArrayList of Driver objects
     */
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public Boolean getRaceStarted() {
        return raceStarted;
    }

    public void setRaceStarted(Boolean started) {
        this.raceStarted = started;
    }
}
