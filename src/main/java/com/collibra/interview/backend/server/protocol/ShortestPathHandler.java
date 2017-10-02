package com.collibra.interview.backend.server.protocol;

public class ShortestPathHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.SHORTEST_PATH);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String[] inputValues = inputMessage.substring(ClientMessages.SHORTEST_PATH.length() +1, inputMessage.length()).split(" ");
        return directedGraph.findShortestPath(inputValues[0], inputValues[1]) + "";
    }
}
