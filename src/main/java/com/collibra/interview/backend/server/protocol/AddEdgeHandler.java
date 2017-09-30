package com.collibra.interview.backend.server.protocol;

public class AddEdgeHandler extends DirectGraphHandler {

    public String handle(String inputMessage) {
        String edgeInformation = inputMessage.substring(9, inputMessage.length());
        String[] edgeValues = edgeInformation.split(" ");
        if (directedGraph.addEdge(edgeValues[0], edgeValues[1], Integer.parseInt(edgeValues[2]))) {
            return "EDGE ADDED";
        } else {
            return "ERROR: NODE NOT FOUND";
        }
    }
}
