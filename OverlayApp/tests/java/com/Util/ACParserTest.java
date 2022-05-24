package com.Util;

import com.Model.Driver;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ACParserTest {

    @Test
    void parseDriverData() throws IOException, FontFormatException {
        ACParser driverACParser = new ACParser(new JSONParser().readConfig("config/config.json"));
        ArrayList<Driver> recvDrivers;

        recvDrivers = driverACParser.parseDriverData("1;Sausage;1;Thomas;Lotus;0.123;1;0;0;123;3:2;Bacon;9;Thomas;Audi;0.000;1;0;1;456;2:3;Missy;7;Thomas;BMW;0.456;0;1;1;789;3");

        assertEquals(1, recvDrivers.get(0).getDriverID());
        assertEquals("Sausage", recvDrivers.get(0).getName());
        assertEquals(2, recvDrivers.get(1).getDriverID());
        assertEquals("Bacon", recvDrivers.get(1).getName());
        assertEquals(3, recvDrivers.get(2).getDriverID());
        assertEquals("Missy", recvDrivers.get(2).getName());
        assertEquals(3, recvDrivers.size());

        assertEquals(1, recvDrivers.get(0).getCompletedLaps());
        assertEquals(9, recvDrivers.get(1).getCompletedLaps());
        assertEquals(7, recvDrivers.get(2).getCompletedLaps());

        assertEquals("Thomas", recvDrivers.get(0).getFocussedDriver());
        assertEquals("Thomas", recvDrivers.get(1).getFocussedDriver());
        assertEquals("Thomas", recvDrivers.get(2).getFocussedDriver());

        assertEquals("Lotus", recvDrivers.get(0).getCar());
        assertEquals("Audi", recvDrivers.get(1).getCar());
        assertEquals("BMW", recvDrivers.get(2).getCar());

        assertEquals("0.123", recvDrivers.get(0).getDelta());
        assertEquals("0.000", recvDrivers.get(1).getDelta());
        assertEquals("0.456", recvDrivers.get(2).getDelta());

        assertEquals(1, recvDrivers.get(0).getOnTrack());
        assertEquals(1, recvDrivers.get(1).getOnTrack());
        assertEquals(0, recvDrivers.get(2).getOnTrack());

        assertEquals(0, recvDrivers.get(0).getRaceStarted());
        assertEquals(0, recvDrivers.get(1).getRaceStarted());
        assertEquals(1, recvDrivers.get(2).getRaceStarted());

        assertEquals(123, recvDrivers.get(0).getFastestLap());
        assertEquals(456, recvDrivers.get(1).getFastestLap());
        assertEquals(789, recvDrivers.get(2).getFastestLap());

    }
}