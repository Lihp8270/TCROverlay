package com.Overlay;

import javax.swing.*;
import java.awt.*;

public class OverlayFrame extends InitFrame {
    private final Dimension dim;
    private JPanel driverPanel;
    private JPanel advertPanel;
    private DriversPanel drivers;
    private AdvertPanel advert;

    /**
     * Constructor for overlay frame
     * @param title Titlle of frame
     * @param visibility default visibility of frame
     */
    public OverlayFrame(String title, Boolean visibility, DriversPanel drivers, AdvertPanel advert) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.drivers = drivers;
        this.advert = advert;
        initialiseFrame();
    }

    private void initialiseFrame() {
        // TODO Set location needs to be done via menu page
        this.frame.setLocation(0,-dim.height);
//        this.frame.setLocation(0,0);
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

        this.frame.add(driverPanel, BorderLayout.WEST);
        this.frame.add(advertPanel, BorderLayout.SOUTH);

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
}
