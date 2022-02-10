package com.Model;

public class Config {
    private int listenPort;
    private int overlayXOffset;
    private int overlayYOffset;
    private String bottomAdvert;
    private int bottomAdvertPadding;
    private int driverListLeftPadding;
    private String positionIcon;
    private String nameBannerIcon;

    public Config() {

    }

    public int getListenPort() {
        return listenPort;
    }

    public int getOverlayXOffset() {
        return overlayXOffset;
    }

    public int getOverlayYOffset() {
        return overlayYOffset;
    }

    public String getBottomAdvert() {
        return bottomAdvert;
    }

    public int getBottomAdvertPadding() {
        return bottomAdvertPadding;
    }

    public int getDriverListLeftPadding() {
        return driverListLeftPadding;
    }

    public String getPositionIcon() {
        return positionIcon;
    }

    public String getNameBannerIcon() {
        return nameBannerIcon;
    }
}
