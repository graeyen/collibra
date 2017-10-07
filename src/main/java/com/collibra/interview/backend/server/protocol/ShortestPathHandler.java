package com.collibra.interview.backend.server.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortestPathHandler extends DirectGraphHandler implements ConversationHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.SHORTEST_PATH);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String[] inputValues = inputMessage.substring(ClientMessages.SHORTEST_PATH.length() +1, inputMessage.length()).split(" ");
        try {
            int weightFound = directedGraph.findShortestPath(inputValues[0], inputValues[1]);
            logger.debug("Weight found: " + weightFound);
            return weightFound + "";
        } catch (IllegalArgumentException e) {
            return "ERROR: NODE NOT FOUND";
        }

    }
}
