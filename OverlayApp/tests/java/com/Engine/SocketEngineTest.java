package com.Engine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SocketEngineTest {

    @Test
    void socketTest() throws IOException {
        SocketEngine client = new SocketEngine("127.0.0.1", 9090);
        client.startConnection();
        String response = client.sendMessage("Hello World");
        System.out.println(response);
        assertEquals("I heard you!", response);
    }
}