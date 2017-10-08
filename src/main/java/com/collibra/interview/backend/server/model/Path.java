package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private Node lastNode;
    private List<String> nodeNames = new ArrayList();
    private int weight;

    private Path() {
        // only for internal use
    }

    public Path(Node node) {
        nodeNames.add(node.getName());
        lastNode = node;
    }

    public Path addNode(Node node, int weightToNode) {

        Path copy = new Path();
        copy.weight = this.weight;
        copy.nodeNames = new ArrayList(this.nodeNames);

        copy.nodeNames.add(node.getName());
        copy.weight = weight + weightToNode;
        copy.lastNode = node;

        return copy;
    }

    public boolean contains(Node node) {
        return nodeNames.contains(node.getName());
    }

    public int getWeight() {
        return weight;
    }

    public List<Edge> getLastNodeEdges() {
        return lastNode.edges;
    }
}