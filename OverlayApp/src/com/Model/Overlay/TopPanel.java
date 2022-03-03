package com.Model.Overlay;

import com.Model.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TopPanel extends InitPanel {
    private Font font;
    private JLabel lapLabel;
    private final ImageIcon icon;
    private String lap;
    private String maxLaps;

    public TopPanel(Config config) throws IOException, FontFormatException {
        super();
        icon = new ImageIcon(config.getLapIcon());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(config.getLapIconTopPadding(),config.getLapIconLeftPadding(),0,0));
        font = Font.createFont(Font.TRUETYPE_FONT, new File("config/Brother1816Black.otf"));
        font = font.deriveFont(Font.BOLD,20);
        this.lap = "0";
        createLapLabel();
    }

    @Override
    public JPanel getPanel() {
        lapLabel.setText(lapText());
        this.panel.add(lapLabel);

        return this.panel;
    }

    public void createLapLabel() {
        lapLabel = new JLabel();
        lapLabel.setForeground(Color.WHITE);
        lapLabel.setIcon(icon);
        lapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lapLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lapLabel.setFont(font);

        lapLabel.setText(lapText());
    }

    public void setLaps(String lap) {
        this.lap = lap;
    }

    public void setMaxLaps(String maxLaps) {
        this.maxLaps = maxLaps;
    }

    public String lapText() {
        return lap + " / " + maxLaps;
    }
}
