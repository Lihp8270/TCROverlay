package com.Overlay;

import javax.swing.JFrame;
import java.awt.*;

public class OverlayFrame {
    JFrame frame;

    public OverlayFrame(String title) {
        frame = new JFrame();

        this.frame.setTitle(title);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        show();
    }

    /**
     * Set size of frame
     * @param width in pixels
     * @param height in pixels
     */
    public void setSize(int width, int height) {
        this.frame.setSize(width, height);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public void hide() {
        this.frame.setVisible(false);
    }

}
