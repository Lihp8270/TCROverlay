package com.Model.Overlay;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Allows transparent overlay
 */
public class ContentPane extends JPanel {

    public ContentPane() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));

        g2d.setColor(getBackground());
        g2d.fill(getBounds());

        g2d.dispose();
    }

}
