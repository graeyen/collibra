package com.collibra.interview.backend.server.core;

import com.collibra.interview.backend.server.protocol.CollibraConversation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraServer {

    private  CollibraConversation collibraConversation;

    public void start(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {

            System.out.println("Server running");

            Socket clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(30 * 1000);

            System.out.println("Client connects");

            // START PROTOCOL
            collibraConversation = CollibraConversation.startNewConversation();

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), Charset.forName("US-ASCII")), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String greeting = collibraConversation.createStartMessage();
            sendMessage(writer, greeting);

            // READ RESPONSES
            String response;

            boolean continueConversation = true;
            do {

                try {
                    response = readResponse(reader);
                    if (response == null) {
                        continueConversation = false;
                    } else {
                        handleResponse(writer, response);
                    }
                } catch (SocketTimeoutException e) {
                    String textOut = createGoodbyMessage();
                    System.out.println("TIMEOUT !. SEND:" + textOut);
                    writer.println(textOut);
                    continueConversation = false;
                } catch(SocketException e) {
                    continueConversation = false;
                }
            } while (continueConversation);


            clientSocket.close();
        }

    }

    private void sendMessage(PrintWriter writer, String greeting) {
        System.out.println("SEND:" + greeting);
        writer.println(greeting);
    }

    private void handleResponse(PrintWriter writer, String response) {
        String message = collibraConversation.handleResponse(response);
        sendMessage(writer, message);
    }

    private String readResponse(BufferedReader reader) throws IOException {
        System.out.println("Waiting for response");
        String response = reader.readLine();

        System.out.println("RECEIVE: " + response);
        return response;
    }

    private String createGoodbyMessage() {
        return collibraConversation.createEndMessage();
    }

}
