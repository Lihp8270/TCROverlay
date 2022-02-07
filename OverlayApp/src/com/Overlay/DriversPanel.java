package com.Overlay;

import com.Model.Driver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Collections;

public class DriversPanel extends InitPanel {
    ArrayList<Driver> drivers;

    public DriversPanel() {
        super();
        drivers = new ArrayList<>();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(0, 15,0,0));
    }

    /**
     * Build and return drivers panel
     * @return Returns completed Drivers panel
     */
    @Override
    public JPanel getPanel() {

        Collections.sort(drivers, Driver.Comparators.currentPos);

        this.panel.add(Box.createVerticalGlue());
        for (Driver driver : drivers) {
            this.panel.add(driver.getBox());
        }
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(Box.createVerticalGlue());

        return this.panel;
    }

    /**
     * Add driver to the driver list
     * @param driver completed driver box containing position, name bar and position change labelss
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    /**
     * Accessor for driver array list
     * @return ArrayList of Driver objects
     */
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

}
