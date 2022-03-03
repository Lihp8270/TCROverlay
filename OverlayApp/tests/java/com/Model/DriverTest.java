package com.Model;

import com.Util.JSONParser;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void getPosDiff() throws IOException, FontFormatException {
        Driver testDriver = new Driver(1, "test", new JSONParser().readConfig("config/config.json"));
        testDriver.setCurrentPos(3);
        testDriver.setStartingPos(2);
        assertEquals(1, testDriver.getPosDiff());

        testDriver.setCurrentPos(1);
        testDriver.setStartingPos(2);
        assertEquals(1, testDriver.getPosDiff());

        testDriver.setCurrentPos(10);
        testDriver.setStartingPos(2);
        assertEquals(8, testDriver.getPosDiff());

        testDriver.setCurrentPos(29);
        testDriver.setStartingPos(1);
        assertEquals(28, testDriver.getPosDiff());
    }
}