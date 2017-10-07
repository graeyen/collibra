package com.collibra.interview.backend.server.model;

import java.util.List;

public class Edge {
    private Node targetNode;
    private int weight;

    public Edge(Node targetNode) {
        this(targetNode, 0);
    }

    public Edge(Node targetNode, int weight) {
        this.targetNode = targetNode;
        this.weight = weight;
    }

    public List<Edge> getEdgesTargetNode() {
        return targetNode.edges;
    }

    @Override
    public boolean equals(Object otherEdge) {
        if (otherEdge == null) {
            return false;
        }
        if (!(otherEdge instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) otherEdge;
        return other.targetNode.equals(targetNode);
    }


    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        return content.append("(->")
                .append(targetNode.getNodeName() + "+" + weight)
                .append(")").toString();
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public int getWeight() {
        return weight;
    }
}
