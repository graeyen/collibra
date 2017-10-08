package com.collibra.interview.backend.server.algorithm;

import com.collibra.interview.backend.server.model.DirectedGraph;
import com.collibra.interview.backend.server.model.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ShorterThanFinderTest {

    private DirectedGraph directedGraph;
    private ShorterThanFinder shorterThanFinder;

    @Before
    public void setup() {
        directedGraph = new DirectedGraph();
        shorterThanFinder = new ShorterThanFinder();
    }

    @Test
    public void testFindNoEdges() {
        directedGraph.addNode("node1");
        List<String> result = shorterThanFinder.findNodesInRangeFor(getNode("node1"), 5);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFinOneEdgeInRange() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 5);
        List<String> result = shorterThanFinder.findNodesInRangeFor(getNode("node1"), 6);

        Assert.assertTrue(result.size() == 1);
        Assert.assertTrue(result.contains("node2"));
    }

    @Test
    public void testFindInRangeWithMultipleEdges() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");
        directedGraph.addEdge("node1", "node2", 5);
        directedGraph.addEdge("node1", "node3", 2);
        directedGraph.addEdge("node1", "node4", 4);
        directedGraph.addEdge("node1", "node5", 3);

        List<String> result = shorterThanFinder.findNodesInRangeFor(getNode("node1"), 4);

        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(result.contains("node3"));
        Assert.assertTrue(result.contains("node5"));
    }

    @Test
    public void testFindInRange2Jumps() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");

        directedGraph.addEdge("node1", "node2", 4);
        directedGraph.addEdge("node2", "node3", 3);

        List<String> result = shorterThanFinder.findNodesInRangeFor(getNode("node1"), 8);

        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(result.contains("node3"));
        Assert.assertTrue(result.contains("node2"));


    }


    private Node getNode(String name) {
        return directedGraph.getNode(name);
    }
}
