package com.collibra.interview.backend.server.algorithm;

import com.collibra.interview.backend.server.model.Edge;
import com.collibra.interview.backend.server.model.Node;
import com.collibra.interview.backend.server.model.Path;

import java.util.ArrayList;
import java.util.List;

public class ShorterThanFinder {

    public List<String> findNodesInRangeFor(Node node, int weight) {

        List<String> nodesInRange = new ArrayList();

        List<Path> paths = setupStartPath(node);

        boolean doKeepOnWalking;

        do {
            doKeepOnWalking = false;

            List<Path> extendedPaths = new ArrayList<>();

            for(Path path : paths) {
                List<Edge> edges = path.getLastNodeEdges();

                // walk all edges
                for(Edge edge : edges) {
                    Node targetNode = edge.getTargetNode();

                    // We are not interested if the node is startnode or node is already part of path (loop).
                    if(targetNode.hasName(node.getName()) || path.contains(targetNode)) {
                        continue;
                    }
                    // Extend a copy of the path with one node
                    Path extendedPath = path.addNode(targetNode, edge.getWeight());
                    // only if new node is in range, we will keep it.
                    if(extendedPath.getWeight() < weight) {
                        nodesInRange.add(targetNode.getName());
                        extendedPaths.add(extendedPath);
                        doKeepOnWalking=true;
                    }

                }
            }
            // for the next iteration, the extended paths become the paths to be extended.
            paths = extendedPaths;

        } while(doKeepOnWalking);


        return nodesInRange;
    }

    private List<Path> setupStartPath(Node startNode) {
        List<Path> paths = new ArrayList();
        Path startPath = new Path(startNode);
        paths.add(startPath);
        return paths;
    }
}
