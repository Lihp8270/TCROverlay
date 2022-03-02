package com.Overlay;

import javax.swing.*;
import java.awt.*;

public class DriverLabel extends JLabel {
    private final ImageIcon icon;

    /**
     * Formatted driver label
     * @param name String
     * @param icon Image Icon of driver banner
     */
    public DriverLabel(String name, ImageIcon icon) {
        this.icon = icon;
        Dimension imageDims = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        this.setText("  " + name);
        this.setMinimumSize(imageDims);
        this.setMaximumSize(imageDims);
        this.setPreferredSize(imageDims);
        this.setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), 0, 0, icon.getIconWidth(), icon.getIconHeight(), null);
        super.paintComponent(g);
    }
}
