package com.Util;

import com.Model.Driver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ParserTest {

    @Test
    void parseDriverData() {
        Parser driverParser = new Parser();
        ArrayList<Driver> recvDrivers;

        recvDrivers = driverParser.parseDriverData("1;Sausage;3:2;Bacon;2:3;Missy;3");

        assertEquals(1, recvDrivers.get(0).getDriverID());
        assertEquals("Sausage", recvDrivers.get(0).getName());
        assertEquals(2, recvDrivers.get(1).getDriverID());
        assertEquals("Bacon", recvDrivers.get(1).getName());
        assertEquals(3, recvDrivers.get(2).getDriverID());
        assertEquals("Missy", recvDrivers.get(2).getName());
        assertEquals(3, recvDrivers.size());

    }
}