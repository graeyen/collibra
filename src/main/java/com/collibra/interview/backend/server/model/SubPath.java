package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class SubPath {

    int weight;

    List<Node> nodesPassed;
    List<Edge> edgesToWalk;


    public SubPath(int weight, List<Edge> edgesToWalk, List<Node> nodesPassed) {
        this.weight = weight;
        this.edgesToWalk = edgesToWalk;
        this.nodesPassed = new ArrayList(nodesPassed);
    }
}
