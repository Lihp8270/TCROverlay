package com.Engine;

import com.Overlay.InitFrame;
import com.Overlay.OverlayFrame;
import com.Overlay.MenuFrame;

import javax.swing.*;

public class OverlayEngine {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OverlayFrame overlayFrame = new OverlayFrame("Overlay", false);
            MenuFrame menuFrame = new MenuFrame("TCR Overlay Menu", overlayFrame, true);

        });
    }
}
