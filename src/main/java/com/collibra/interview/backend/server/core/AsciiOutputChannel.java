package com.collibra.interview.backend.server.core;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

public class AsciiOutputChannel {

    private PrintWriter writer;

    public AsciiOutputChannel(Socket socket) {

        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("US-ASCII")), true);
        } catch (IOException e) {
           throw new RuntimeException("Failed to setup the output channel", e);
        }
    }

    public void sendMessage(String message) {
        System.out.println("SEND:" + message);
        writer.println(message);
    }

    public void close() {
        try {
            writer.close();
        } catch (Exception e) {
            // we tried but failed
        }

    }


}
