package com.Model.Overlay;

import com.Model.Config;
import com.Model.Driver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class DriversPanel extends InitPanel {
    ArrayList<Driver> drivers;
    private JLabel timingTreeHeader;
    private Box headerBox;
    private int maxDrivers;
    private Boolean spectator;
    private Config config;

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
        this.spectator = false;
    }

    /**
     * Build and return drivers panel
     * @param focussedDriver Name of focussed Driver
     * @param mode 0 = delta to leader, 1 = delta to car ahead
     * @return Returns completed Drivers panel
     */
    public JPanel getPanel(String focussedDriver, int mode, TopPanel lapPanel) {
        int driverCount = 1;
        double carAheadDelta = 0.000;

        Collections.sort(drivers, Driver.Comparators.currentPos);

        this.panel.add(headerBox);
        this.panel.add(Box.createRigidArea(new Dimension(0,3)));
        for (Driver driver : drivers) {
            for (String spectatorCar : config.getSpectatorCars()) {
                if (driver.getName().equals(spectatorCar)) {
                    spectator = true;
                } else {
                    spectator = false;
                }
            }

            if (!spectator) {
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

    /**
     * Clear driver list
     */
    public void clearPanel() {
        drivers.clear();
    }

}
