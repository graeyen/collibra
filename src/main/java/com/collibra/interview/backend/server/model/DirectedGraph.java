package com.collibra.interview.backend.server.model;

import com.collibra.interview.backend.server.algorithm.PathFinder;
import com.collibra.interview.backend.server.algorithm.ShorterThanFinder;

import java.util.List;

public class DirectedGraph {

    private Nodes nodes = new Nodes();

    public boolean addNode(String nodeName) {
        return nodes.add(nodeName);
    }

    public boolean removeNode(String nodeName) {
        return nodes.remove(new Node(nodeName));
    }

    public boolean addEdge(String nodeName1, String nodeName2, int weight) {
        if (doesNotExists(nodeName1) || doesNotExists(nodeName2)) {
            return false;
        }

        Node node1 = nodes.getNodeByName(nodeName1);
        Node node2 = nodes.getNodeByName(nodeName2);

        node1.edges.add(new Edge(node2, weight));
        return true;
    }


    public boolean removeEdge(String nodeName1, String nodeName2) {
        if(doesNotExists(nodeName1)) {
            return false;
        }

        Node node1 = nodes.getNodeByName(nodeName1);
        return node1.edges.remove(new Edge(Node.fromName(nodeName2)));
    }

    public int findShortestPath(String startNodeName, String targetNodeName) {
        Node startNode = nodes.getNodeByName(startNodeName);
        Node targetNode = nodes.getNodeByName(targetNodeName);

        if (startNode != null && targetNode != null) {
            return new PathFinder().findShortestPath(startNode, targetNode);
        }
        throw new IllegalArgumentException("Node(s) not existing");
    }

    public List<String> findCloserThan(int weight, String nodeName) {
        return new ShorterThanFinder().findNodesInRangeFor(nodes.getNodeByName(nodeName), weight);
    }

    private boolean doesNotExists(String nodeName1) {
        return !nodes.contains(new Node(nodeName1));
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append("[");
        for(Node aNode : nodes) {
            content.append("[");
            content.append(aNode.getName() + ":");
            for (Edge anEdge : aNode.edges) {
                content.append("(->");
                content.append(anEdge.getTargetNode().getName() + "+" + anEdge.getWeight());
                content.append(")");
            }
            content.append("]");
        }

        content.append("]");

        return content.toString();
    }

    public void clear() {
        nodes.clear();
    }

    public Node getNode(String nodeName) {
        return nodes.getNodeByName(nodeName);
    }

}

