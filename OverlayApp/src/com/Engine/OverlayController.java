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
    private JSONParser jsonParser;
    private OverlayFrame overlayFrame;
    private DriversPanel drivers;
    private AdvertPanel advert;
    private SocketEngine acConnector;
    private ACParser driverACParser;
    private SwingWorker backgroundWorker;
    private Boolean running;
    private Boolean connected;

    public OverlayController(String configFile) throws IOException {
        jsonParser = new JSONParser();
        config = jsonParser.readConfig(configFile);
        drivers = new DriversPanel(config);
        advert = new AdvertPanel(config);
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
        Boolean driverFound = false;
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
     * @throws IOException
     */
    public void connectAC() throws IOException {
        if(!connected) {
            acConnector.startConnection();
            connected = true;
        }
    }

    /**
     * Disconnect from server
     * @throws IOException
     */
    public void disconnectAC() throws IOException {
        acConnector.stopConnection();
        connected = false;

        System.out.println("disconnected");
    }

    /**
     * Get updated driver info from server
     * @return Returns updated ArrayList of Drivers
     * @throws IOException
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
     * @throws IOException
     */
    public void run() throws IOException {
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
     * @throws IOException
     */
    public void stop() throws IOException {
        if(connected) {
            this.backgroundWorker.cancel(true);
            running = false;
            this.backgroundWorker = null;
        }
    }

}
