package com.Engine;

import com.Model.Config;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketEngine {
    private DatagramSocket serverSocket;
    private int bufferMultiplier;
    private boolean safe;
    private final int port;
    private final int bufferSize;

    public SocketEngine(int port, int bufferSize) {
        this.port = port;
        this.bufferSize = bufferSize;
        this.bufferMultiplier = 1;
        this.safe = false;
    }

    /**
     * Retrieve from server
     */
    public String retrieveFromClient() throws IOException {
        String UDPStream = null;

        while (!safe) {
            serverSocket = new DatagramSocket(port);
            byte[] buf = new byte[bufferSize * bufferMultiplier];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            serverSocket.receive(packet);

            UDPStream = new String(packet.getData(), 0, packet.getLength());

            serverSocket.close();

            if (UDPStream.substring(UDPStream.length() - 1).equalsIgnoreCase(":")) {
                bufferSafe();
            } else {
                bufferMultiplier = bufferMultiplier * 2;
            }
        }

         bufferUnsafe();
        return UDPStream;
    }

    private void bufferSafe() {
        this.safe = true;
    }

    private void bufferUnsafe() {
        this.safe = false;
    }

}