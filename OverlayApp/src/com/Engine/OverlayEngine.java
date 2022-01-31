package com.Engine;

import com.Overlay.OverlayFrame;
import com.Overlay.MenuFrame;

import javax.swing.SwingUtilities;

public class OverlayEngine {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                OverlayFrame overlayFrame = new OverlayFrame("Overlay");
                MenuFrame menuFrame = new MenuFrame("TCR Overlay Menu", overlayFrame);

                overlayFrame.setSize(800, 600);
                menuFrame.setSize(800,600);

            }
        });
    }
}
