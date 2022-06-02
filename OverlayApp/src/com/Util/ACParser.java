package com.Util;

import com.Model.Config;
import com.Model.Driver;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ACParser {
    private final Config config;

    public ACParser(Config config) {
        this.config = config;
    }

    public ArrayList<Driver> parseDriverData(String data) throws IOException, FontFormatException {
        ArrayList<Driver> newDrivers = new ArrayList<>();

        String[] splitStrings = data.split(":");

        for (String driverString : splitStrings) {
            String[] driverData = driverString.split(";");

            newDrivers.add(new Driver(Integer.valueOf(driverData[0]), driverData[1], config));
            newDrivers.get(newDrivers.size() - 1).setCompletedLaps(Integer.valueOf(driverData[2]));
            newDrivers.get(newDrivers.size() - 1).setFocussedDriver(driverData[3]);
            newDrivers.get(newDrivers.size() - 1).setCar(driverData[4]);
            newDrivers.get(newDrivers.size() - 1).setDelta(driverData[5]);
            newDrivers.get(newDrivers.size() - 1).setOnTrack(Integer.valueOf(driverData[6]));
            newDrivers.get(newDrivers.size() - 1).setRaceStarted(Integer.valueOf(driverData[7]));
            newDrivers.get(newDrivers.size() - 1).setSessionReset(Integer.valueOf(driverData[8]));
            if (Long.valueOf(driverData[9]) == 0) {
                newDrivers.get(newDrivers.size() - 1).setFastestLap(999999L);
            } else {
                newDrivers.get(newDrivers.size() - 1).setFastestLap(Long.valueOf(driverData[9]));
            }
            newDrivers.get(newDrivers.size() - 1).setCurrentPos(Integer.valueOf(driverData[11]));
            newDrivers.get(newDrivers.size() - 1).setStartingPos(Integer.valueOf(driverData[10]));
        }

        return newDrivers;
    }

}
