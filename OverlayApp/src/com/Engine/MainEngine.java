package com.Engine;

import com.Overlay.OverlayFrame;
import com.Overlay.MenuFrame;

import javax.swing.*;
import java.io.IOException;

public class MainEngine {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OverlayFrame overlayFrame = new OverlayFrame("Overlay", false);
            MenuFrame menuFrame = new MenuFrame("TCR Overlay Menu", overlayFrame, true);

        });
    }
}
