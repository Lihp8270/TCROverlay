package com.Overlay;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

public class OverlayFrame extends InitFrame {
    private JLabel testLabel;
    private final Dimension dim;

    public OverlayFrame(String title, Boolean visibility) {
        super(title, visibility);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        initialiseFrame();
    }

    private void initialiseFrame() {
        // TODO Set location needs to be done via menu page
        this.frame.setLocation(0,-dim.height);
        this.frame.setSize(dim);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(null);
        this.frame.setUndecorated(true);
        this.frame.setBackground(new Color(0,255,0,0));

        this.frame.setContentPane(new ContentPane());
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setLayout(new BorderLayout());

        testLabel = new JLabel("Test Label");
        testLabel.setBounds(50, 100, 100, 30);

        // TODO Remove test code
        this.frame.add(testLabel);
        testLabel.setLayout(null);
        testLabel.setForeground(Color.WHITE);
        testLabel.setVisible(true);
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
