package com.Engine;

import com.Model.Driver;
import com.Overlay.AdvertPanel;
import com.Overlay.DriversPanel;
import com.Overlay.OverlayFrame;

public class OverlayController<drivers> {
    private OverlayFrame overlayFrame;
    private DriversPanel drivers;
    private AdvertPanel advert;

    public OverlayController() {
        drivers = new DriversPanel();
        advert = new AdvertPanel();
        overlayFrame = new OverlayFrame("Overlay", false, drivers, advert);
    }

    /**
     * Get the overlay frame
     * @return OverlayFrame
     */
    public OverlayFrame getOverlayFrame() {
        return overlayFrame;
    }

    /**
     * Update driver information
     */
    public void updateDrivers() {
        this.drivers.addDriver(new Driver(5, "Tom"));
        this.drivers.addDriver(new Driver(6, "Mark"));
        this.drivers.addDriver(new Driver(7, "Emma"));
        this.drivers.addDriver(new Driver(8, "Missy"));
        overlayFrame.updateDrivers(drivers);
    }
}
