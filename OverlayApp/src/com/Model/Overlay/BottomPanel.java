package com.Model.Overlay;

import com.Model.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BottomPanel extends  InitPanel {
    private Box driverNameBox;
    private JLabel bannerIcon;
    private JLabel driverNameBannerLabel;
    private Font font;
    private final ImageIcon icon;
    private final ImageIcon driverNameBanner;
    private Config config;

    public BottomPanel(Config config) throws IOException, FontFormatException {
        super();
        this.config = config;
        icon = new ImageIcon(config.getLargeNameBannerIcon());
        driverNameBanner = new ImageIcon(config.getLargeNameBanner());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
        this.panel.setBorder(new EmptyBorder(0,config.getDriverNameLeftPadding(),config.getDriverNameBottomPadding(),0));
        font = Font.createFont(Font.TRUETYPE_FONT, new File("config/Brother1816Black.otf"));
        font = font.deriveFont(Font.BOLD,25);

        createLogoLabel();
        createDriverLabel();
        createBox();
    }

    @Override
    public JPanel getPanel() {
        this.panel.add(driverNameBox);
        this.panel.add(Box.createRigidArea(new Dimension(3,0)));
        this.panel.add(driverNameBannerLabel);

        return this.panel;
    }

    public void createBox() {
        driverNameBox = Box.createHorizontalBox();
        driverNameBox.add(bannerIcon);
    }

    public void createLogoLabel() {
        bannerIcon = new JLabel();
        bannerIcon.setIcon(icon);
    }

    public void createDriverLabel() {
        driverNameBannerLabel = new JLabel();
        driverNameBannerLabel.setForeground(Color.WHITE);
        driverNameBannerLabel.setIcon(driverNameBanner);
        driverNameBannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        driverNameBannerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        driverNameBannerLabel.setFont(font);
    }

    public void setDriverName(String driverName) {
        driverNameBannerLabel.setText(driverName);
    }

    public void setCarLogo(String carName) {
        String[] carList = config.getCarList();
        String[] logoList = config.getCarLogo();
        int carLogoIndex = 0;

        for (int i = 0; i < carList.length; i++) {
            if (carList[i].equals(carName)) {
                carLogoIndex = i;
            }
        }

        bannerIcon.setIcon(new ImageIcon(logoList[carLogoIndex]));
    }

}
