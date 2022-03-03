package com.Util;

import com.Model.Config;
import com.Model.Driver;

import java.util.ArrayList;

public class ACParser {
    private final Config config;

    public ACParser(Config config) {
        this.config = config;
    }

    public ArrayList<Driver> parseDriverData(String data) {
        ArrayList<Driver> newDrivers = new ArrayList<>();

        String[] splitStrings = data.split(":");

        for (String driverString : splitStrings) {
            String[] driverData = driverString.split(";");

            newDrivers.add(new Driver(Integer.valueOf(driverData[0]), driverData[1], config));
            newDrivers.get(newDrivers.size() - 1).setCompletedLaps(Integer.valueOf(driverData[2]));
            newDrivers.get(newDrivers.size() - 1).setFocussedDriver(driverData[3]);
            newDrivers.get(newDrivers.size() - 1).setCurrentPos(Integer.valueOf(driverData[4]));
            newDrivers.get(newDrivers.size() - 1).setStartingPos(Integer.valueOf(driverData[4]));
        }

        return newDrivers;
    }

}