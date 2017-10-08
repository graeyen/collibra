package com.collibra.interview.backend.server.protocol.handlers;

import com.collibra.interview.backend.server.algorithm.ShorterThanFinder;
import com.collibra.interview.backend.server.model.Node;
import com.collibra.interview.backend.server.protocol.ClientMessages;
import com.collibra.interview.backend.server.protocol.SessionContext;

import java.util.Collections;
import java.util.List;

public class CloserThanHandler extends DirectGraphHandler implements ConversationHandler {

    private ShorterThanFinder shorterThanFinder = new ShorterThanFinder();

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.CLOSER_THAN);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {

        String[] inputValues = inputMessage.substring(12, inputMessage.length()).split(" ");
        int weight = Integer.valueOf(inputValues[0]);
        String nodeName = inputValues[1];

        Node node = directedGraph.getNode(nodeName);
        if(node == null) {
            return "ERROR: NODE NOT FOUND";
        }

        return formatList(shorterThanFinder.findNodesInRangeFor(node, weight));
    }

    private String formatList(List<String> result) {
        Collections.sort(result);

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for(String node: result) {
            if(isFirst) {
                builder.append(node);
                isFirst = false;
            } else {
                builder.append("," + node);
            }

        }
        return builder.toString();
    }
}
