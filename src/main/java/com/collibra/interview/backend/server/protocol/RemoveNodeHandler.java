package com.collibra.interview.backend.server.protocol;

public class RemoveNodeHandler extends DirectGraphHandler {

    public String handle(String inputMessage) {
        String nodeName = inputMessage.substring(12, inputMessage.length());
        if (directedGraph.removeNode(nodeName)) {
            return "NODE REMOVED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
