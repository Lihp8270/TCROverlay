package com.Engine;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocketEngineTest {

    @Test
    void socketTest() throws IOException {
        SocketEngine client = new SocketEngine("127.0.0.1", 9090);
        client.startConnection();
        String response = client.retrieveFromClient();
        System.out.println(response);
        assertEquals("test", response);
        client.stopConnection();
    }
}