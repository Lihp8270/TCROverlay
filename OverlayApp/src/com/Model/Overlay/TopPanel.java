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
    private boolean finalLap;

    public TopPanel(Config config) throws IOException, FontFormatException {
        super();
        icon = new ImageIcon(config.getLapIcon());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(config.getLapIconTopPadding(),config.getLapIconLeftPadding(),0,0));
        font = Font.createFont(Font.TRUETYPE_FONT, new File(config.getLapCountFont()));
        font = font.deriveFont(Font.BOLD,config.getLapCountFontSize());
        this.lap = "0";
        this.finalLap = false;

        createLapLabel();
    }

    @Override
    public JPanel getPanel() {
        lapLabel.setText(lapText());
        this.panel.add(lapLabel);

        return this.panel;
    }

    /**
     * Create the lap counter label
     */
    public void createLapLabel() {
        lapLabel = new JLabel();
        lapLabel.setForeground(Color.WHITE);
        lapLabel.setIcon(icon);
        lapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lapLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lapLabel.setFont(font);

        lapLabel.setText(lapText());
    }

    /**
     * Set lap count
     * @param lap current lap
     */
    public void setLaps(String lap) {
        if(!finalLap) {
            this.lap = lap;
        }

        if (this.lap.equals(maxLaps)) {
            finalLap = true;
        }
    }

    /**
     * Set maximum laps on the label
     * @param maxLaps maximum number of laps
     */
    public void setMaxLaps(String maxLaps) {
        this.maxLaps = maxLaps;
    }

    /**
     * Build lap text label
     * @return Concat of laps / max laps
     */
    public String lapText() {
        return lap + " / " + maxLaps;
    }

    /**
     * Gets max race laps as integer
     * @return integer max laps
     */
    public int getMaxLaps() {
        return Integer.valueOf(maxLaps);
    }
}
