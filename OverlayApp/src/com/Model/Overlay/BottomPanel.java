package com.Model.Overlay;

import com.Model.Config;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BottomPanel extends  InitPanel {
    private Box driverNameBox;
    private JLabel bannerIcon;
    private JLabel driverNameBannerLabel;
    private Font font;
    private final ImageIcon icon;
    private final ImageIcon driverNameBanner;

    public BottomPanel(Config config) {
        super();
        icon = new ImageIcon(config.getLargeNameBannerIcon());
        driverNameBanner = new ImageIcon(config.getLargeNameBanner());
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
        this.panel.setBorder(new EmptyBorder(0,config.getDriverNameLeftPadding(),config.getDriverNameBottomPadding(),0));
        font = new Font("Brother 1816", Font.BOLD, 25);

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

}
