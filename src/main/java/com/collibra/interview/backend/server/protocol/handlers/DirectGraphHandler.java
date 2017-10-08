package com.collibra.interview.backend.server.protocol.handlers;

import com.collibra.interview.backend.server.model.DirectedGraph;

public abstract class DirectGraphHandler {

    protected static DirectedGraph directedGraph = new DirectedGraph();

    public static DirectedGraph getGraph() {
        return directedGraph;
    }
}
