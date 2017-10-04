package com.collibra.interview.backend.server.model;

import java.util.*;

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

    private int findWeight(String targetNode, Node startNode) {

        Deque<SubRoute> routeQueue = new ArrayDeque();

        int finalWeight = Integer.MAX_VALUE;
        int weight = 0;

        List<Edge> edges = startNode.edges;

        while(!edges.isEmpty()) {

            if (edges.size() > 1) {

                List<Edge> edgesMinusOne = new ArrayList();
                for (int i=1; i< edges.size(); i++) {
                    edgesMinusOne.add(edges.get(i));
                }
                routeQueue.addFirst(new SubRoute(weight, edgesMinusOne));
            }

            Edge edge = edges.get(0);

            weight = weight + edge.weight;
            if (edge.targetNode.nodeName.equals(targetNode)) {
                if (weight < finalWeight) {
                    finalWeight = weight;
                }

                if(routeQueue.isEmpty()) {
                    return finalWeight;
                } else {
                    SubRoute subRoute = routeQueue.removeFirst();
                    edges = subRoute.continuesEdges;

                    weight =  subRoute.weight;


                }



            } else {
                edges = edge.targetNode.edges;

                if(edges.isEmpty() && !routeQueue.isEmpty()) {
                    SubRoute subRoute = routeQueue.removeFirst();
                    edges = subRoute.continuesEdges;
                    weight = subRoute.weight;
                   

                }
            }
        }
        return finalWeight;
    }

    public int calculateWeightsEdges(List<Edge> edges) {
        int weight = 0;
        for(Edge edge: edges) {
            weight = weight + edge.weight;
        }

        return weight;
    }



//        int weight = 0;
//        int finalWeight = Integer.MAX_VALUE;
//
//        List<Edge> currentEdges = startNode.edges;
//
//        while (!currentEdges.isEmpty()) {
//            for (Edge edge : currentEdges) {
//                if (edge.targetNode.nodeName.equals(targetNode)) {
//                    if(weight + edge.weight < finalWeight) {
//                        finalWeight = weight + edge.weight;
//                    }
//                    weight = 0;
//                }
//                if(currentEdges.size() > 1) {
//                    List<Edge> otherPossibleEdges = new ArrayList();
//                    for(int i=1; i<currentEdges.size(); i++) {
//                        otherPossibleEdges.add(currentEdges.get(i));
//                    }
//                }
//                currentEdges = edge.targetNode.edges;
//                weight = weight + edge.weight;
//            }
//        }
//
//        return finalWeight;


    private int findWeight2(String nodeNameToFind, Node currentNode) {
        int weightFound = Integer.MAX_VALUE;
        for(Edge edge : currentNode.edges) {
            if(edge.targetNode.nodeName.equals(nodeNameToFind) && edge.weight < weightFound) {
                weightFound = edge.weight;
            } else {
                int nextWeight = findWeight2(nodeNameToFind, edge.targetNode);
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
                content.append(anEdge.targetNode.nodeName + "+" + anEdge.weight);
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
        private Node targetNode;
        private int weight;

        public Edge(Node targetNode) {
            this(targetNode, 0);
        }
        public Edge(Node targetNode, int weight) {
            this.targetNode = targetNode;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            Edge other = (Edge) obj;
            return other.targetNode.equals(targetNode);
        }
    }

    static private class SubRoute {
        private int weight;
        private List<Edge> continuesEdges;

        private SubRoute(int weight, List<Edge> continuesEdges) {
            this.weight = weight;
            this.continuesEdges = continuesEdges;
        }

    }
}

