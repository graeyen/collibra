package com.collibra.interview.backend.server.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathFinderTest {

    private DirectedGraph directedGraph;

    @Before
    public void setup() {
        directedGraph = new DirectedGraph();
    }

    @Test
    public void testFindShortestPathNoPath() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");

        int result = directedGraph.findShortestPath("node1", "node2");
        Assert.assertEquals(Integer.MAX_VALUE, result);

    }

    @Test
    public void testFindShortestPathOnePathTwoNodes() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 3);
        int result = directedGraph.findShortestPath("node1", "node2");
        Assert.assertEquals(3, result);

    }


    @Test
    public void testFindShortestPathOnePathThreeNodes() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);

        int result = directedGraph.findShortestPath("node1", "node3");
        Assert.assertEquals(5, result);

    }

    @Test
    public void testFindShortestPathOnePathFourNodes() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node4", 4);

        int result = directedGraph.findShortestPath("node1", "node4");
        Assert.assertEquals(9, result);

    }

    @Test
    public void testFindShortestPathOnePathFourNodesNoTarget() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node4", 4);

        int result = directedGraph.findShortestPath("node1", "node5");
        Assert.assertEquals(Integer.MAX_VALUE, result);

    }

    @Test
    public void testFindShortestPathOnePathFiveNodesSplitTop() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node2", "node5", 6);
        directedGraph.addEdge("node3", "node4", 4);

        int result = directedGraph.findShortestPath("node1", "node4");
        Assert.assertEquals(9, result);

    }

    @Test
    public void testFindShortestPathOnePathFiveNodesSplitBottom() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node2", "node4", 8);
        directedGraph.addEdge("node3", "node5", 4);

        int result = directedGraph.findShortestPath("node1", "node4");
        Assert.assertEquals(11, result);

    }

    @Test
    public void testFindShortestPathOnePathFiveNodeTopFastest() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node5", 4);
        directedGraph.addEdge("node2", "node4", 4);
        directedGraph.addEdge("node4", "node5", 8);

        int result = directedGraph.findShortestPath("node1", "node5");
        Assert.assertEquals(9, result);

    }

    @Test
    public void testFindShortestPathOnePathFiveNodeBottomFastest() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node5", 8);
        directedGraph.addEdge("node2", "node4", 2);
        directedGraph.addEdge("node4", "node5", 1);

        int result = directedGraph.findShortestPath("node1", "node5");
        Assert.assertEquals(6, result);

    }

    @Test
    public void testFindShortestPathMultipleBranches() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");
        directedGraph.addNode("node6");
        directedGraph.addNode("node7");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node4", 5);
        directedGraph.addEdge("node2", "node5", 6);
        directedGraph.addEdge("node5", "node6", 4);
        directedGraph.addEdge("node5", "node7", 8);

        int result = directedGraph.findShortestPath("node1", "node7");
        Assert.assertEquals(17, result);

    }
}
