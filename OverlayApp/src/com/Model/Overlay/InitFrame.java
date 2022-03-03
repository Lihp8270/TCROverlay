package com.Model.Overlay;

import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class InitFrame {
    protected JFrame frame;
    protected Boolean visible;
    protected GraphicsEnvironment gEnv;
    protected GraphicsDevice[] gDev;

    public InitFrame(String title, Boolean visible) {
        frame = new JFrame();
        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDev = gEnv.getScreenDevices();

        this.frame.setTitle(title);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(visible);
        this.visible = visible;
    }

    /**
     * Set size of frame
     * @param width in pixels
     * @param height in pixels
     */
    public void setSize(int width, int height) {
        this.frame.setSize(width, height);
    }

    /**
     * Get visibility of frame
     * @return boolean
     */
    public boolean getVisibility() {
        return visible;
    }


}
