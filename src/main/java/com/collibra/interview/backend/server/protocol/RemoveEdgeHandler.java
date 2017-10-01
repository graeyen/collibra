package com.collibra.interview.backend.server.protocol;

public class RemoveEdgeHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.REMOVE_EDGE);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String edgeInformation = inputMessage.substring(12, inputMessage.length());
        String[] edgeValues = edgeInformation.split(" ");
        if (directedGraph.removeEdge(edgeValues[0], edgeValues[1])) {
            return "EDGE REMOVED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
