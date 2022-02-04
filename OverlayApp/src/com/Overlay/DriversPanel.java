package com.Overlay;

import javax.swing.*;
import java.util.ArrayList;

public class DriversPanel extends InitPanel {
    ArrayList<JLabel> drivers;

    public DriversPanel() {
        super();
        drivers = new ArrayList<>();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
    }

    @Override
    public JPanel getPanel() {
        panel.add(Box.createVerticalGlue());
        for (JLabel driver : drivers) {
            panel.add(driver);
        }
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    public void addDriver(JLabel driver) {
        drivers.add(driver);
    }
}
