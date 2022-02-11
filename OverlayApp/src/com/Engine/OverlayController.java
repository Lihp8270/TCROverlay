package com.Engine;

import com.Model.Config;
import com.Model.Driver;
import com.Overlay.AdvertPanel;
import com.Overlay.DriversPanel;
import com.Overlay.OverlayFrame;
import com.Util.ACParser;
import com.Util.JSONParser;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class OverlayController {
    protected Config config;
    private final OverlayFrame overlayFrame;
    private final DriversPanel drivers;
    private final SocketEngine acConnector;
    private final ACParser driverACParser;
    private SwingWorker backgroundWorker;
    private boolean running;
    private boolean connected;

    public OverlayController(String configFile) throws IOException {
        JSONParser jsonParser = new JSONParser();
        config = jsonParser.readConfig(configFile);
        drivers = new DriversPanel(config);
        AdvertPanel advert = new AdvertPanel(config);
        driverACParser = new ACParser(config);
        acConnector = new SocketEngine(config.getListenPort());
        connected = false;
        running = false;
        overlayFrame = new OverlayFrame("Overlay", false, drivers, advert, config);
        backgroundWorker = null;
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
    public void updateDrivers() throws IOException {
        ArrayList<Driver> updatedDrivers;
        boolean driverFound = false;
        int driverFoundIndex = 0;

        updatedDrivers = getUpdate();

        for(Driver driver : updatedDrivers) {
            for(Driver driver1 : drivers.getDrivers()) {
                if(driver.getDriverID() == driver1.getDriverID()) {
                    driverFound = true;
                }
                if(!driverFound) {
                    driverFoundIndex++;
                }
            }

            if(driverFound) {
                drivers.getDrivers().get(driverFoundIndex).setCurrentPos(driver.getCurrentPos());
                drivers.getDrivers().get(driverFoundIndex).setPosDiff();
                drivers.getDrivers().get(driverFoundIndex).setChangeDir();
                driverFound = false;
                driverFoundIndex = 0;
            } else {
                drivers.addDriver(driver);
            }
        }

        overlayFrame.updateDrivers(drivers);
    }

    /**
     * Connect to AC Server
     */
    private void connectAC() throws IOException {
        if(!connected) {
            acConnector.startConnection();
            connected = true;
        }
    }

    /**
     * Disconnect from server
     */
    private void disconnectAC() throws IOException {
        acConnector.stopConnection();
        connected = false;
    }

    /**
     * Get updated driver info from server
     * @return Returns updated ArrayList of Drivers
     */
    private ArrayList<Driver> getUpdate() throws IOException {
        if (!connected) {
            return null;
        }
        ArrayList<Driver> updatedDriverList;

        String UDPStream = acConnector.retrieveFromClient();
        updatedDriverList = driverACParser.parseDriverData(UDPStream);

        return updatedDriverList;
    }

    /**
     * Gets Overlay running status
     * @return boolean
     */
    public Boolean getRunning() {
        return running;
    }

    /**
     * Start overlay updating
     */
    public void run() throws IOException {
        connectAC();

        if(connected) {
            running = true;

            SwingWorker sw = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    while(true) {
                        updateDrivers();
                        Thread.sleep(100);
                    }
                }
            };

            this.backgroundWorker = sw;

            sw.execute();
        }
    }

    /**
     * Stop overlay updating
     */
    public void stop() throws IOException {
        if(connected) {
            this.backgroundWorker.cancel(true);
            running = false;
            this.backgroundWorker = null;
            disconnectAC();
        }
    }

    /**
     * Reset panel
     */
    public void reset() {
        drivers.clearPanel();
    }
}
