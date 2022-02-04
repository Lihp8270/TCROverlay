package com.Overlay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class DriversPanel extends InitPanel {
    ArrayList<Box> driversTest;

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
        for (Box driver : driversTest) {
            this.panel.add(driver);
        }
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(Box.createVerticalGlue());

        return this.panel;
    }

    /**
     * Add driver to the driver list
     * @param driver completed driver box containing position, name bar and position change labelss
     */
    public void addDriver(Box driver) {
        driversTest.add(driver);
    }
}
