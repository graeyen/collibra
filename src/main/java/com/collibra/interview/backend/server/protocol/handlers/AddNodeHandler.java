package com.collibra.interview.backend.server.protocol.handlers;

import com.collibra.interview.backend.server.protocol.ClientMessages;
import com.collibra.interview.backend.server.protocol.SessionContext;

public class AddNodeHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.ADD_NODE);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String nodeName = inputMessage.substring(9, inputMessage.length());
        if (directedGraph.addNode(nodeName)) {
            return "NODE ADDED";
        } else {
            return "ERROR: NODE ALREADY EXISTS";
        }
    }
}
