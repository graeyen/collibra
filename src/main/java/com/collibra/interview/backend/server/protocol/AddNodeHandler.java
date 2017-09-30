package com.collibra.interview.backend.server.protocol;

import com.collibra.interview.backend.server.model.DirectedGraph;

public class AddNodeHandler extends DirectGraphHandler {

    public String handle(String inputMessage) {
        String nodeName = inputMessage.substring(9, inputMessage.length());
        if (directedGraph.addNode(nodeName)) {
            return "NODE ADDED";
        } else {
            return "ERROR: NODE ALREADY EXISTS";
        }
    }
}
