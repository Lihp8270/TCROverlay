package com.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketEngine {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final String ip;
    private final int port;

    public SocketEngine(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public void startConnection() throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);

        try {
            String resp = in.readLine();

            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Cannot read line";
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();

    }
}
