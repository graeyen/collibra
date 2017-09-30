package com.collibra.interview.backend.server.core;

import com.collibra.interview.backend.server.protocol.Conversation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraServer {

    private static final int DEFAULT_TIME_OUT_MS = 30 * 1000;

    public void start(int port) {

        ServerSocket serverSocket = setupSocket(port);

        System.out.println(String.format("Server running on port [%s]", port));

        while (true) {

            Socket clientSocket = listenForConnection(serverSocket);

            // Setup communication channels
            AsciiOutputChannel outputChannel = new AsciiOutputChannel(clientSocket);
            AsciiInputChannel inputChannel = new AsciiInputChannel(clientSocket);

            // Start the conversation with the client
            Conversation conversation = Conversation.create();
            outputChannel.sendMessage(conversation.start());

            // READ RESPONSES
            String inputMessage;

            boolean doContinueConversation = true;
            do {

                try {
                    inputMessage = inputChannel.read();
                    if (inputMessage == null) {
                        doContinueConversation = false;
                    } else {
                        outputChannel.sendMessage(conversation.answer(inputMessage));
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("TIMEOUT !");
                    outputChannel.sendMessage(conversation.end());
                    doContinueConversation = false;

                } catch(SocketException e) {
                    doContinueConversation = false;
                } catch(IOException e) {
                    e.printStackTrace();
                    // lets keep on trying reading messages.
                }
            } while (doContinueConversation);

            inputChannel.close();
            outputChannel.close();
            closeSocket(clientSocket);

        }

    }

    private void closeSocket(Socket clientSocket) {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket listenForConnection(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(DEFAULT_TIME_OUT_MS);
            return clientSocket;
        } catch (IOException e) {
            throw new RuntimeException("There was a problem listening for a connection", e);
        }
    }

    private ServerSocket setupSocket(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create the socket", e);
        }
        return serverSocket;
    }

}
