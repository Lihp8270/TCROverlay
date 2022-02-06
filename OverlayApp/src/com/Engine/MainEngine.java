package com.Engine;

import com.Overlay.MenuFrame;

import javax.swing.*;

public class MainEngine {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame("TCR Overlay", true);
        });
    }
}
