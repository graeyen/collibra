package com.collibra.interview.backend.server.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    public static final int NO_ROUTE_WEIGHT = Integer.MAX_VALUE;
    private static final long MAX_NR_WALKS = 2500;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public int findShortestPath(Node startNode, Node targetNode) {

        // corner case where we don't need to look for pathways
        if(startNode.equals(targetNode)) {
            return 0;
        }

        // Default
        int finalWeight = NO_ROUTE_WEIGHT;
        long nrWalks = 0;

        List<Path> paths = setupStartPath(startNode);

        boolean doKeepOnWalking;

        // start walking the paths.
        do {
            nrWalks++;
            doKeepOnWalking = false;

            logger.debug("Start walking walk nr [%s]", nrWalks);
            List<Path> extendedPaths = new ArrayList<>();
            for(Path path : paths) {
                List<Edge> edges = path.getLastNodeEdges();

                // walk all edges
                for(Edge edge : edges) {
                    Node extraNode = edge.targetNode;

                    // Loop found, break of walking this edge.
                    if(path.contains(extraNode)) {
                       continue;
                    }

                    // Extend a copy of the path with one node
                    Path extendedPath = path.addNode(extraNode, edge.weight);

                    // Path already longer than another one, break off walking this edge.
                    if(extendedPath.getWeight() >= finalWeight) {
                        continue;
                    }

                    // found our target !
                    if(extraNode.hasName(targetNode.nodeName)) {
                        finalWeight = extendedPath.getWeight();

                    // Target not yet found, continue this path in the next iteration
                    } else {
                        doKeepOnWalking = true;
                        extendedPaths.add(extendedPath);
                    }
                }
            }
            // for the next iteration, the extended paths become the paths to be extended.
            paths = extendedPaths;

        // Added a saveguard to avoid crashing the application when to many walks are invoked.
        } while(doKeepOnWalking && nrWalks < MAX_NR_WALKS);

        logger.debug(String.format("Number of steps executed: [%s]", nrWalks));
        return finalWeight;

    }

    private List<Path> setupStartPath(Node startNode) {
        List<Path> paths = new ArrayList();
        Path startPath = new Path(startNode);
        paths.add(startPath);
        return paths;
    }
}
