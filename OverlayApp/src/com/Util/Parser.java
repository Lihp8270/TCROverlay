package com.Util;

import com.Model.Driver;

import java.util.ArrayList;

public class Parser {
    public Parser() {

    }

    public ArrayList<Driver> parseDriverData(String data) {
        ArrayList<Driver> newDrivers = new ArrayList<>();

        String[] splitStrings = data.split(":");

        for (String driverString : splitStrings) {
            String[] driverData = driverString.split(";");

            newDrivers.add(new Driver(Integer.valueOf(driverData[0]), driverData[1]));
            newDrivers.get(newDrivers.size() - 1).setCurrentPos(Integer.valueOf(driverData[2]));
        }

        return newDrivers;
    }

}
