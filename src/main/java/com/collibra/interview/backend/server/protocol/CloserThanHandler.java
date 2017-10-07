package com.collibra.interview.backend.server.protocol;

import java.util.List;

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

        return formatList(directedGraph.findCloserThan(weight, node));
    }

    private String formatList(List<String> result) {
        return "";
    }
}
