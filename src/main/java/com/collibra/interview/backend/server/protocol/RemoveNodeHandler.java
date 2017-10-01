package com.collibra.interview.backend.server.protocol;

public class RemoveNodeHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.REMOVE_NODE);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String nodeName = inputMessage.substring(12, inputMessage.length());
        if (directedGraph.removeNode(nodeName)) {
            return "NODE REMOVED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
