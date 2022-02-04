package com.Overlay;

import com.Model.Driver;

import javax.swing.*;
import java.awt.*;

public class OverlayFrame extends InitFrame {
    private final Dimension dim;

    /**
     * Constructor for overlay frame
     * @param title Titlle of frame
     * @param visibility default visibility of frame
     */
    public OverlayFrame(String title, Boolean visibility) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
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

        // TODO Remove test code
        InitPanel advertPanel = new InitPanel();

        JLabel testLabel2 = new JLabel();
        ImageIcon testLogo = new ImageIcon("assets/HostedOnSimracing.png");
        testLabel2.setIcon(testLogo);
        advertPanel.getPanel().add(testLabel2);

        DriversPanel drivers = new DriversPanel();
        drivers.addDriver(new Driver(1, "Tom").getBox());
        drivers.addDriver(new Driver(2, "Mark").getBox());
        drivers.addDriver(new Driver(3, "Emma").getBox());
        drivers.addDriver(new Driver(4, "Missy").getBox());

        this.frame.add(drivers.getPanel(), BorderLayout.WEST);
        this.frame.add(advertPanel.getPanel(), BorderLayout.SOUTH);
        testLabel2.setVisible(true);
        // End of test code
    }

    /**
     * Toggle overlay visibility
     */
    public void toggleVisibility(int screen){
        if (visible) {
            this.frame.setVisible(false);
//            gDev[screen].setFullScreenWindow(null);
            visible = false;
        } else {
//            gDev[screen].setFullScreenWindow(this.frame);
            this.frame.setVisible(true);
            visible = true;
        }
    }
}
