package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class FasterPathFinder {

    public int findShortestPath(Node startNode, Node targetNode) {

        int finalWeight = Integer.MAX_VALUE;

        List<WeightedPath> paths = new ArrayList();

        boolean doExtension = true;

        WeightedPath startPath = new WeightedPath(startNode);
        paths.add(startPath);

        while(doExtension) {
            doExtension = false;
            List<WeightedPath> extendedPaths = new ArrayList<>();
            for(WeightedPath path : paths) {
                List<Edge> edges = path.lastNode.edges;
                for(Edge edge : edges) {
                    Node extraNode = edge.targetNode;
                    if(!path.contains(edge.targetNode.nodeName)) {

                        WeightedPath extendedPath = path.copy();
                        extendedPath.addNode(edge.targetNode.nodeName, edge.weight);

                        if(extendedPath.weight >= finalWeight) {
                            continue;
                        }

                        if(extraNode.nodeName.equals(targetNode.nodeName)) {
                            finalWeight = extendedPath.weight;
                            //finalPaths.add(extendedPath);
                        } else {
                            doExtension = true;
                            extendedPaths.add(extendedPath);
                            extendedPath.lastNode = edge.targetNode;
                        }

                    }

                }
            }
            paths = extendedPaths;
        }



          return finalWeight;

    }


    private static class WeightedNode {
        private Node node;
        private int weight;

        public WeightedNode(Node node) {
            this(node, 0);
        }

        public WeightedNode(Node node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            WeightedNode other = (WeightedNode) obj;

            return other.node.equals(node);
        }
    }

    private static class WeightedPath {
        private Node lastNode;
        private List<String> nodeNames = new ArrayList();
        private int weight;

        private WeightedPath() {

        }
        private WeightedPath(Node node) {
            nodeNames.add(node.nodeName);
            lastNode = node;
        }

        private WeightedPath copy() {
            WeightedPath copy = new WeightedPath();
            copy.weight = this.weight;
            copy.nodeNames = new ArrayList(this.nodeNames);

            return copy;
        }

        private void addNode(String name, int nodeWeight) {
            nodeNames.add(name);
            weight = weight + nodeWeight;
        }

        private boolean contains(String nodeName) {
            return nodeNames.contains(nodeName);
        }
    }
}
