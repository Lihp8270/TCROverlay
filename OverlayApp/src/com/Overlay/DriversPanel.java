package com.Overlay;

import com.Model.Driver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class DriversPanel extends InitPanel {
    ArrayList<Driver> driversTest;

    public DriversPanel() {
        super();
        driversTest = new ArrayList<>();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(0, 15,0,0));
    }

    /**
     * Build and return drivers panel
     * @return Returns completed Drivers panel
     */
    @Override
    public JPanel getPanel() {
        this.panel.add(Box.createVerticalGlue());
        for (Driver driver : driversTest) {
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
        driversTest.add(driver);
    }
}
