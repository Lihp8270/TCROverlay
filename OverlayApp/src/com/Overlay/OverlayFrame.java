package com.Overlay;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OverlayFrame extends InitFrame {
    private JLabel testLabel;

    public OverlayFrame(String title, Boolean visibility) {
        super(title, visibility);

        testLabel = new JLabel("Test Label");
        testLabel.setBounds(50,100,100,30);

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLayout(null);

        this.frame.setUndecorated(true);
        this.frame.setOpacity(0.55f);
        // TODO Remove test code
        this.frame.add(testLabel);
        testLabel.setLayout(null);
        testLabel.setVisible(true);
        this.frame.setUndecorated(true);
        // End of test code
    }

    /**
     * Toggle overlay visibility
     */
    public void toggleVisibility(int screen) {
        if (visible) {
            this.frame.setVisible(false);
            gDev[screen].setFullScreenWindow(null);
            visible = false;
        } else {
            gDev[screen].setFullScreenWindow(this.frame);
            this.frame.setVisible(true);
            visible = true;
        }
    }
}
