package com.Engine;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketEngine {
    private DatagramSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final String ip;
    private final int port;

    public SocketEngine(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Start connection to AC Server
     * @throws IOException
     */
    public void startConnection() throws IOException {
        serverSocket = new DatagramSocket(9090);
    }

    /**
     * Retrieve from server
     */
    public String retrieveFromClient() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        serverSocket.receive(packet);

        String UDPStream = new String(packet.getData(), 0, packet.getLength());

        return UDPStream;
    }

    /**
     * Close connection to AC Server
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        serverSocket.close();
    }
}