package com.Util;

import com.Model.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {

    @Test
    void readConfigTest() {
        JSONParser jsonParser = new JSONParser();
        Config config = jsonParser.readConfig("config/config.json");

        assertEquals(9090, config.getListenPort());
        assertEquals(0, config.getOverlayXOffset());
        assertEquals(10, config.getBottomAdvertPadding());
    }
}