package com.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeParserTest {
    TimeParser timeParser = new TimeParser();

    @Test
    void getSecondsFromMinutesTest() {
        assertEquals(7140, timeParser.getSecondsFromMinutes(119));
        assertEquals(7140, timeParser.getSecondsFromMinutes("119"));
    }

    @Test
    void getTimeRemainingTest() {
        assertEquals("01:30", timeParser.getTimeRemainingFromSeconds(90));
        assertEquals("60:00", timeParser.getTimeRemainingFromSeconds(3600));
        assertEquals("60:00", timeParser.getTimeRemainingFromSeconds(timeParser.getSecondsFromMinutes("60")));
    }

}