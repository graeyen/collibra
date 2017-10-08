package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String nodeName;
    List<Edge> edges = new ArrayList();

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public static Node fromName(String name) {
        return new Node(name);
    }


    @Override
    public boolean equals(Object otherNode) {
        if(otherNode == null) {
            return false;
        }
        if(!(otherNode instanceof Node)) {
            return false;
        }

        Node other = (Node) otherNode;
        return other.nodeName.equals(nodeName);
    }

    public boolean hasName(String name) {
        return nodeName.equals(name);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public String getName() {
        return nodeName;
    }
}