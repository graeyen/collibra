package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {

    private List<String> nodes = new ArrayList();

    public boolean addNode(String nodeName) {
        if(doesNotExists(nodeName)) {
            nodes.add(nodeName);
            return true;
        }
        return false;
    }

    public boolean removeNode(String nodeName) {
        return nodes.remove(nodeName);
    }

    public boolean addEdge(String nodeName1, String nodeName2, int weight) {
        if (doesNotExists(nodeName1) || doesNotExists(nodeName2)) {
            return false;
        }
        return true;
    }


    public boolean removeEdge(String nodeName1, String nodeName2) {
        if(doesNotExists(nodeName1)) {
            return false;
        }
        return true;
    }

    private boolean doesNotExists(String nodeName1) {
        return !nodes.contains(nodeName1);
    }
}
