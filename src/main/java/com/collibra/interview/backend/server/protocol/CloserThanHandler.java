package com.collibra.interview.backend.server.protocol;

public class CloserThanHandler extends DirectGraphHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.CLOSER_THAN);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String[] inputValues = inputMessage.substring(12, inputMessage.length()).split(" ");
        int weight = Integer.valueOf(inputValues[0]);
        String node = inputValues[1];

        return directedGraph.closerThan(weight, node).toString();
    }
}
