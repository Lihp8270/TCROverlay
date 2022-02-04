package com.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void getPosDiff() {
        Driver testDriver = new Driver(1, "test");
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