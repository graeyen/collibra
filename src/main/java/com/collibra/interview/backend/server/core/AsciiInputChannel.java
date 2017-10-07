package com.collibra.interview.backend.server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AsciiInputChannel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
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
        logger.info("Received: " + response);

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
