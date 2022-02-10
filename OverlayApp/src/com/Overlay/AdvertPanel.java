package com.Overlay;


import com.Model.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdvertPanel extends InitPanel {
    private final ImageIcon logo;

    public AdvertPanel(Config config) {
        super();
        logo = new ImageIcon(config.getBottomAdvert());
        this.panel.setBorder(new EmptyBorder(0, 0, config.getBottomAdvertPadding(), 0));
    }

    /**
     * Build and return drivers panel
     * @return Returns completed Drivers panel
     */
    @Override
    public JPanel getPanel() {
        JLabel adLabel = new JLabel();
        adLabel.setIcon(logo);
        adLabel.setVisible(true);
        this.panel.add(adLabel);

        return this.panel;
    }
}
