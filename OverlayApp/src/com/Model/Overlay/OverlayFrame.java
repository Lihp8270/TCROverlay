package com.Model.Overlay;

import com.Model.Config;

import javax.swing.*;
import java.awt.*;

public class OverlayFrame extends InitFrame {
    private final Dimension dim;
    private JPanel driverPanel;
    private JPanel advertPanel;
    private JPanel driverNamePanel;
    private JPanel lapPanel;
    private DriversPanel drivers;
    private AdvertPanel advert;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private final Config config;
    private String currentFocussedDriver;
    private int lastLapDisplayed;

    /**
     * Constructor for overlay frame
     * @param title Title of frame
     * @param visibility default visibility of frame
     */
    public OverlayFrame(String title, Boolean visibility, DriversPanel drivers, AdvertPanel advert, BottomPanel bottomPanel, TopPanel topPanel, Config config) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.drivers = drivers;
        this.advert = advert;
        this.config = config;
        this.bottomPanel = bottomPanel;
        this.topPanel = topPanel;
        this.currentFocussedDriver = "";
        this.lastLapDisplayed = 0;
        initialiseFrame();
    }

    private void initialiseFrame() {
        this.frame.setLocation(config.getOverlayXOffset(),config.getOverlayYOffset());
        this.frame.setSize(dim);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setUndecorated(true);
        this.frame.setBackground(new Color(0,255,0,0));
        this.frame.setAlwaysOnTop(true);

        this.frame.setContentPane(new ContentPane());
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setLayout(new BorderLayout());

        driverPanel = drivers.getPanel();
        advertPanel = advert.getPanel();
        driverNamePanel = bottomPanel.getPanel();
        lapPanel = topPanel.getPanel();

        this.frame.add(driverPanel, BorderLayout.WEST);
        this.frame.add(advertPanel, BorderLayout.EAST);
        this.frame.add(driverNamePanel, BorderLayout.SOUTH);
        this.frame.add(lapPanel, BorderLayout.CENTER);
    }

    /**
     * Toggle overlay visibility
     */
    public void toggleVisibility() {
        if (visible) {
            this.frame.setVisible(false);
            visible = false;
        } else {
            this.frame.setVisible(true);
            visible = true;
        }
    }


    /**
     * Update Drivers panel
     * @param drivers new DriverPanel to use
     */
    public void updateDrivers(DriversPanel drivers) {
        this.driverPanel.removeAll();
        this.driverPanel = drivers.getPanel();
        this.frame.add(driverPanel, BorderLayout.WEST);
        this.frame.repaint();
        this.frame.revalidate();
    }

    public void updateLargeName(String driverName) {
        if(!(currentFocussedDriver.equals(driverName))) {
            this.driverNamePanel.removeAll();
            bottomPanel.setDriverName(driverName);

            for (int i = 0; i < drivers.getDrivers().size(); i++) {
                if (drivers.getDrivers().get(i).getName().equals(driverName)) {
                    bottomPanel.setCarLogo(drivers.getDrivers().get(i).getCar());
                }
            }

            this.driverNamePanel = bottomPanel.getPanel();
            this.frame.add(driverNamePanel, BorderLayout.SOUTH);
            this.frame.repaint();
            this.frame.revalidate();

            currentFocussedDriver = driverName;
        }
    }

    public void updateLapGraphic() {
        if(!(lastLapDisplayed == drivers.getDrivers().get(0).getCompletedLaps() + 1)) {
            this.lapPanel.removeAll();
            topPanel.setLaps(String.valueOf(drivers.getDrivers().get(0).getCompletedLaps() + 1));
            this.lapPanel = topPanel.getPanel();
            this.frame.add(lapPanel, BorderLayout.CENTER);
            this.frame.repaint();
            this.frame.revalidate();
            lastLapDisplayed = drivers.getDrivers().get(0).getCompletedLaps() + 1;
        }
    }

    public void setMaxLaps(String maxLaps) {
        topPanel.setMaxLaps(maxLaps);
    }
}
