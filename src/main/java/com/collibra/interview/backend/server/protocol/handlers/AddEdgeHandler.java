package com.collibra.interview.backend.server.protocol.handlers;

import com.collibra.interview.backend.server.protocol.ClientMessages;
import com.collibra.interview.backend.server.protocol.SessionContext;

public class AddEdgeHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.ADD_EDGE);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String edgeInformation = inputMessage.substring(9, inputMessage.length());
        String[] edgeValues = edgeInformation.split(" ");
        if (directedGraph.addEdge(edgeValues[0], edgeValues[1], Integer.parseInt(edgeValues[2]))) {
            return "EDGE ADDED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
