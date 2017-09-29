package com.collibra.interview.backend.server;

import com.collibra.interview.backend.server.protocol.*;

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

    private static CollibraProtocol collibraProtocol = new CollibraProtocol();

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(50000);

        while (true) {

            Socket clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(30 * 1000);

            System.out.println("Client connects");

            // START PROTOCOL
            SessionContext sessionContext = new SessionContext(System.currentTimeMillis());

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), Charset.forName("US-ASCII")), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String greeting = collibraProtocol.start();
            System.out.println("SEND:" + greeting);
            writer.println(greeting);

            // READ RESPONSES
            String response;

            boolean continueConversation = true;
            do {

                try {
                    response = readResponse(reader);
                    if (response == null) {
                        continueConversation = false;
                    } else {
                        handleResponse(writer, response, sessionContext);
                    }
                } catch (SocketTimeoutException e) {
                    String textOut = createGoodbyMessage(sessionContext);
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

    private static void handleResponse(PrintWriter writer, String response, SessionContext sessionContext) {
        if (response.startsWith(("HI, I'M"))) {
            String textOut = new ClientGreetResponseHandler().handle(response, sessionContext);
            System.out.println("SEND:" + textOut);
            writer.println(textOut);
        } else {
            String textOut = new DefaultResponseHandler().handle(response, sessionContext);
            System.out.println("SEND:" + textOut);
            writer.println(textOut);
        }
    }

    private static String readResponse(BufferedReader reader) throws IOException {
        System.out.println("Waiting for response");
        String response = reader.readLine();

        System.out.println("RECEIVE: " + response);
        return response;
    }

    private static String createGoodbyMessage(SessionContext sessionContext) {
        return new EndConversationHandler().handle(null, sessionContext);
    }

}
