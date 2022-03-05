package com.Engine;

import com.Model.Config;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketEngine {
    private DatagramSocket serverSocket;
    private final int port;
    private final int bufferSize;

    public SocketEngine(int port, int bufferSize) {
        this.port = port;
        this.bufferSize = bufferSize;
    }

    /**
     * Retrieve from server
     */
    public String retrieveFromClient() throws IOException {
        serverSocket = new DatagramSocket(port);
        byte[] buf = new byte[bufferSize];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        serverSocket.receive(packet);

        String UDPStream = new String(packet.getData(), 0, packet.getLength());

        serverSocket.close();
        return UDPStream;
    }

}