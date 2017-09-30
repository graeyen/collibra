package com.collibra.interview.backend.server.protocol;

public class RemoveEdgeHandler extends DirectGraphHandler {

    public String handle(String inputMessage) {
        String edgeInformation = inputMessage.substring(12, inputMessage.length());
        String[] edgeValues = edgeInformation.split(" ");
        if (directedGraph.removeEdge(edgeValues[0], edgeValues[1])) {
            return "EDGE REMOVED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
