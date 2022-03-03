package com.Engine;

import com.Model.Overlay.MenuFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainEngine {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MenuFrame menuFrame = new MenuFrame("TCR Overlay", true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });
    }
}
