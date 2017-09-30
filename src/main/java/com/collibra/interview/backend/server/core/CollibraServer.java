package com.collibra.interview.backend.server.core;

import com.collibra.interview.backend.server.protocol.CollibraConversation;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraServer {

    private static final int DEFAULT_TIME_OUT_MS = 30 * 1000;

    public void start(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println(String.format("Server running on port [%s]", port));

        while (true) {

            Socket clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(DEFAULT_TIME_OUT_MS);

            System.out.println("Client connects");

            // Setup communication channels
            AsciiOutputChannel outputChannel = new AsciiOutputChannel(clientSocket);
            AsciiInputChannel inputChannel = new AsciiInputChannel(clientSocket);

            // START PROTOCOL
            CollibraConversation conversation = CollibraConversation.startNewConversation();
            outputChannel.sendMessage(conversation.createStartMessage());

            // READ RESPONSES
            String inputMessage;

            boolean doContinueConversation = true;
            do {

                try {
                    inputMessage = inputChannel.read();
                    if (inputMessage == null) {
                        doContinueConversation = false;
                    } else {
                        outputChannel.sendMessage(conversation.createMessageForInput(inputMessage));
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("TIMEOUT !");
                    outputChannel.sendMessage(conversation.createEndMessage());
                    doContinueConversation = false;

                } catch(SocketException e) {
                    doContinueConversation = false;
                }
            } while (doContinueConversation);

            inputChannel.close();
            outputChannel.close();
            clientSocket.close();

        }

    }

}
