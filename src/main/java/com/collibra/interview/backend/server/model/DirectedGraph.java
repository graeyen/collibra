package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {

    private List<Node> nodes = new ArrayList();

    public boolean addNode(String nodeName) {
        if(doesNotExists(nodeName)) {
            nodes.add(new Node(nodeName));
            return true;
        }
        return false;
    }

    public boolean removeNode(String nodeName) {
        return nodes.remove(new Node(nodeName));
    }

    public boolean addEdge(String nodeName1, String nodeName2, int weight) {
        if (doesNotExists(nodeName1) || doesNotExists(nodeName2)) {
            return false;
        }

        Node node1 = nodes.get(nodes.indexOf(Node.fromName(nodeName1)));
        Node node2 = nodes.get(nodes.indexOf(Node.fromName(nodeName2)));

        node1.edges.add(new Edge(node2, weight));
        return true;
    }


    public boolean removeEdge(String nodeName1, String nodeName2) {
        if(doesNotExists(nodeName1)) {
            return false;
        }

        Node node1 = nodes.get(nodes.indexOf(Node.fromName(nodeName1)));
        return node1.edges.remove(new Edge(Node.fromName(nodeName2)));
    }

    public int findShortestPath(String nodeName1, String nodeName2) {
        int index = nodes.indexOf(Node.fromName(nodeName1));
        if (index != -1) {
            Node node1 = nodes.get(index);
            return findWeight(nodeName2, node1);

        }
        return Integer.MAX_VALUE;
    }

    private int findWeight(String nodeNameToFind, Node currentNode) {
        int weightFound = Integer.MAX_VALUE;
        for(Edge edge : currentNode.edges) {
            if(edge.destinationNode.nodeName.equals(nodeNameToFind) && edge.weight < weightFound) {
                weightFound = edge.weight;
            } else {
                int nextWeight = findWeight(nodeNameToFind, edge.destinationNode);
                if (nextWeight != Integer.MAX_VALUE) {
                    weightFound = edge.weight + nextWeight;
                }

            }
        }
        return weightFound;
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
            content.append(aNode.nodeName + ":");
            for (Edge anEdge : aNode.edges) {
                content.append("(->");
                content.append(anEdge.destinationNode.nodeName + "+" + anEdge.weight);
                content.append(")");
            }
            content.append("]");
        }

        content.append("]");

        return content.toString();
    }


    static private class Node {
        private String nodeName;
        private List<Edge> edges = new ArrayList();



        private Node(String nodeName) {
            this.nodeName = nodeName;
        }

        private static Node fromName(String name) {
            return new Node(name);
        }


        @Override
        public boolean equals(Object obj) {
            Node other = (Node) obj;
            return other.nodeName.equals(nodeName);
        }
    }

    static private class Edge {
        private Node destinationNode;
        private int weight;

        public Edge(Node destinationNode) {
            this(destinationNode, 0);
        }
        public Edge(Node destinationNode, int weight) {
            this.destinationNode = destinationNode;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Edge other = (Edge) obj;
            return other.destinationNode.equals(destinationNode);
        }
    }
}

