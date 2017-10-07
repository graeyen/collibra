package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    public static final int NO_ROUTE_WEIGHT = Integer.MAX_VALUE;

    private int weight;
    private List<Edge> edges;
    private List<Node> nodesPassed;

    public int findShortestPath(Node startNode, Node targetNode) {

        // setup
        SubPathsStack subPathsStack = new SubPathsStack();

        int finalWeight = NO_ROUTE_WEIGHT;

        weight = 0;
        edges = startNode.edges;
        nodesPassed = new ArrayList();
        nodesPassed.add(startNode);

        // walk the edges
        while(!edges.isEmpty()) {

            Edge currentEdge = edges.get(0);

            if (edges.size() > 1) {
                pushOtherEdgesToStackForFutureReference(subPathsStack);
            }

            weight = weight + currentEdge.weight;

            // found a path
            if (currentEdgePointsToTargetNode(targetNode.nodeName, currentEdge)) {
                // only override weight if it's a shorter path.
                if (weight < finalWeight) {
                    finalWeight = weight;
                }

                // keep looking for shorter paths if other paths are available.
                if(subPathsStack.isEmpty()) {
                    return finalWeight;
                } else {
                    loadSubPath(subPathsStack.pop());
                }

            // we are looping, continue with another path if available
            } else if(nodesPassed.contains(currentEdge.targetNode)) {
                if(subPathsStack.isEmpty()) {
                    return finalWeight;
                } else {
                    loadSubPath(subPathsStack.pop());
                }

            }
            // let's continue our path with the next available edge.
            else {

                nodesPassed.add(currentEdge.targetNode);
                edges = currentEdge.getEdgesTargetNode();

                if(edges.isEmpty() && !subPathsStack.isEmpty()) {
                    loadSubPath(subPathsStack.pop());
                }
            }
        }
        return finalWeight;
    }

    private boolean currentEdgePointsToTargetNode(String targetNodeName, Edge currentEdge) {
        return currentEdge.targetNode.nodeName.equals(targetNodeName);
    }

    private void pushOtherEdgesToStackForFutureReference(SubPathsStack subRoutesStack) {
        List<Edge> edgesMinusOne = new ArrayList();

        for (int i=1; i< edges.size(); i++) {
            edgesMinusOne.add(edges.get(i));
        }

        subRoutesStack.push(new SubPath(weight, edgesMinusOne, nodesPassed));
    }

    private void loadSubPath(SubPath subPath) {
        edges = subPath.edgesToWalk;
        weight = subPath.weight;
        nodesPassed = subPath.nodesPassed;
    }
}
