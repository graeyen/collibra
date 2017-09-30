package com.collibra.interview.backend.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AsciiInputChannel {

    private BufferedReader reader;

    public AsciiInputChannel(Socket socket) {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to setup input channel", e);
        }
    }

    public String read() throws IOException {

        String response = reader.readLine();
        System.out.println("RECEIVE: " + response);

        return response;
    }

    public void close() {
        try {
            reader.close();
        } catch (Exception e) {
            // we tried but failed
        }

    }
}
