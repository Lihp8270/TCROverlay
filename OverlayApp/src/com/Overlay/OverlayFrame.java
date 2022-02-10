package com.Overlay;

import com.Model.Config;

import javax.swing.*;
import java.awt.*;

public class OverlayFrame extends InitFrame {
    private final Dimension dim;
    private JPanel driverPanel;
    private JPanel advertPanel;
    private DriversPanel drivers;
    private AdvertPanel advert;
    private final Config config;

    /**
     * Constructor for overlay frame
     * @param title Title of frame
     * @param visibility default visibility of frame
     */
    public OverlayFrame(String title, Boolean visibility, DriversPanel drivers, AdvertPanel advert, Config config) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.drivers = drivers;
        this.advert = advert;
        this.config = config;
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
