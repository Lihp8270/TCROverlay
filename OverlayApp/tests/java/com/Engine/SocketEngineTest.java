package com.Engine;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocketEngineTest {

    @Test
    void socketTest() throws IOException {
        SocketEngine client = new SocketEngine(9090);
        client.startConnection();
        String response = client.retrieveFromClient();
        System.out.println(response);
        assertEquals("test", response);
        client.stopConnection();
    }
}