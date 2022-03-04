package com.Engine;

import com.Model.Config;
import com.Model.Overlay.DriverLabel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TimingStackLabelEngine {
    private Config config;
    private final ImageIcon nameBanner;
    private final ImageIcon posBanner;
    private final ImageIcon lapsIcon;
    private final ImageIcon focussedDriverIcon;
    private Font font;

    public TimingStackLabelEngine(Config config) throws IOException, FontFormatException {
        this.config = config;
        this.nameBanner = new ImageIcon(config.getNameBannerName());
        this.posBanner = new ImageIcon(config.getPositionIcon());
        this.lapsIcon = new ImageIcon(config.getNameBannerLaps());
        this.focussedDriverIcon = new ImageIcon(config.getFocussedDriverIcon());
        font = Font.createFont(Font.TRUETYPE_FONT, new File("config/Brother1816Black.otf"));
        font = font.deriveFont(Font.BOLD,15);
    }

    public JLabel getDriverLabel(String name) {
        DriverLabel driverLabel = new DriverLabel(name, nameBanner, font);

        return driverLabel;
    }

    /**
     * Get Position label
     * @return JLabel
     */
    public JLabel getPositionLabel(String currentPos) {
        JLabel positionLabel = new JLabel(currentPos);
        positionLabel.setForeground(Color.BLACK);
        positionLabel.setIcon(posBanner);
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        positionLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        positionLabel.setFont(font);

        return positionLabel;
    }

    public JLabel getFocussedDriverLabel() {
        JLabel focussedDriverLabel = new JLabel();
        focussedDriverLabel.setForeground(Color.BLACK);
        focussedDriverLabel.setIcon(focussedDriverIcon);
        focussedDriverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        focussedDriverLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        focussedDriverLabel.setFont(font);

        return focussedDriverLabel;
    }

    public JLabel getPositionChangeLabel(String changeDir, String posDiff) {
        ImageIcon posChangeIcon;
        JLabel positionChangeLabel = new JLabel();

        if (changeDir.equals("+")) {
            posChangeIcon = new ImageIcon(config.getNameBannerPosUp());
            positionChangeLabel.setText(String.valueOf(posDiff));
        } else if (changeDir.equals("-")) {
            posChangeIcon = new ImageIcon(config.getNameBannerPosDown());
            positionChangeLabel.setText(String.valueOf(posDiff));
        } else {
            posChangeIcon = new ImageIcon(config.getNameBannerPosSteady());
            positionChangeLabel.setText("");
        }

        positionChangeLabel.setForeground(Color.WHITE);
        positionChangeLabel.setIcon(posChangeIcon);
        positionChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        positionChangeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        positionChangeLabel.setFont(font);

        return positionChangeLabel;
    }

    public JLabel getLapsLabel(String laps) {
        JLabel lapsLabel = new JLabel(String.valueOf(laps));
        lapsLabel.setForeground(Color.WHITE);
        lapsLabel.setIcon(lapsIcon);
        lapsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lapsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lapsLabel.setFont(font);

        return lapsLabel;
    }

}
