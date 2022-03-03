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

        recvDrivers = driverACParser.parseDriverData("1;Sausage;1;Thomas;3:2;Bacon;9;Thomas;2:3;Missy;7;Thomas;3");

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

    }
}