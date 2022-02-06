package com.Engine;

import com.Model.Driver;
import com.Overlay.AdvertPanel;
import com.Overlay.DriversPanel;
import com.Overlay.OverlayFrame;
import com.Util.Parser;

import java.io.IOException;
import java.util.ArrayList;


public class OverlayController<drivers> {
    private OverlayFrame overlayFrame;
    private DriversPanel drivers;
    private AdvertPanel advert;
    private SocketEngine acConnector;
    private Parser driverParser;

    private Boolean connected;

    public OverlayController() throws IOException {
        drivers = new DriversPanel();
        advert = new AdvertPanel();
        driverParser = new Parser();
        acConnector = new SocketEngine("127.0.0.1", 9090);
        connected = false;
        overlayFrame = new OverlayFrame("Overlay", false, drivers, advert);
    }

    /**
     * Get the overlay frame
     * @return OverlayFrame
     */
    public OverlayFrame getOverlayFrame() {
        return overlayFrame;
    }

    // TODO
    // Update driver info if driver exists, add new driver if they do not
    /**
     * Update driver information
     */
    public void updateDrivers() throws IOException {
        ArrayList<Driver> updatedDrivers;

        updatedDrivers = getUpdate();
        for(Driver driver : updatedDrivers) {
            drivers.addDriver(driver);
        }

        overlayFrame.updateDrivers(drivers);
    }

    public void connectAC() throws IOException {
        acConnector.startConnection();
        connected = true;
    }

    public void disconnectAC() throws IOException {
        acConnector.stopConnection();
        connected = false;
    }

    public ArrayList<Driver> getUpdate() throws IOException {
        if (!connected) {
            return null;
        }
        ArrayList<Driver> updatedDriverList;

        String resp = acConnector.sendMessage("Send Data");
        updatedDriverList = driverParser.parseDriverData(resp);

        return updatedDriverList;

    }
}
