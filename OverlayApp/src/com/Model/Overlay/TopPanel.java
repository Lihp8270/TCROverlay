package com.Model.Overlay;

import com.Model.Config;
import com.Util.TimeParser;

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
    private int secondsRemaining;
    private boolean finalLap;
    private TimeParser timeParser;
    private int sessionMode;

    public TopPanel(Config config) throws IOException, FontFormatException {
        super();
        icon = new ImageIcon(config.getLapIcon());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.setBorder(new EmptyBorder(config.getLapIconTopPadding(),config.getLapIconLeftPadding(),0,0));
        font = Font.createFont(Font.TRUETYPE_FONT, new File(config.getLapCountFont()));
        font = font.deriveFont(Font.BOLD,config.getLapCountFontSize());
        this.lap = "0";
        this.secondsRemaining = 0;
        this.finalLap = false;
        timeParser = new TimeParser();
        this.sessionMode = 0;

        createLapLabel();
    }

    @Override
    public JPanel getPanel() {
        lapLabel.setText(lapText(sessionMode));
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

        lapLabel.setText(lapText(sessionMode));
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
        setSessionMode(1);
        this.maxLaps = maxLaps;
    }

    /**
     * Set seconds remaining from given minutes
     * @param mins number of minutes for the session
     */
    public void setSecondsRemaining(String mins) {
        setSessionMode(2);
        this.secondsRemaining = timeParser.getSecondsFromMinutes(mins);
    }

    /**
     * Build lap text label
     * @param mode 1 = laps, 2 = timed
     * @return Concat of laps / max laps
     */
    public String lapText(int mode) {
        switch(mode) {
            case 1:
                return lap + " / " + maxLaps;
            case 2:
                return timeParser.getTimeRemainingFromSeconds(secondsRemaining);
            default:
                return "NOT SET";
        }
    }

    /**
     * Gets max race laps as integer
     * @return integer max laps
     */
    public int getMaxLaps() {
        return Integer.valueOf(maxLaps);
    }

    /**
     * Sets session mode
     * @param mode 1 = Laps, 2 = Timed
     */
    private void setSessionMode(int mode) {
        this.sessionMode = mode;
    }

}
