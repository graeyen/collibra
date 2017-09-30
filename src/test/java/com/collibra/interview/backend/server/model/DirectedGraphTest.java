package com.collibra.interview.backend.server.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DirectedGraphTest {

    private DirectedGraph directedGraph;

    @Before
    public void setup() {
        directedGraph = new DirectedGraph();
    }

    @Test
    public void testAddNotExistingNode() {
        boolean result = directedGraph.addNode("Node1");
        Assert.assertTrue(result);
    }

    @Test
    public void testAddExistingNode() {
        directedGraph.addNode("Node1");
        boolean result = directedGraph.addNode("Node1");
        Assert.assertFalse(result);
    }

    @Test
    public void testAddEdgeToExistingNodes() {
        directedGraph.addNode("Node1");
        directedGraph.addNode("Node2");
        boolean result = directedGraph.addEdge("Node1", "Node2", 1);
        Assert.assertTrue(result);

    }

    @Test
    public void testAddEdgeWhereFirstNodeMissing() {
        directedGraph.addNode("Node2");
        boolean result = directedGraph.addEdge("Node1", "Node2", 1);
        Assert.assertFalse(result);

    }

    @Test
    public void testAddEdgeWhereSecondNodeMissing() {
        directedGraph.addNode("Node1");
        boolean result = directedGraph.addEdge("Node1", "Node2", 1);
        Assert.assertFalse(result);

    }

    @Test
    public void testRemoveExistingNode() {
        directedGraph.addNode("node1");
        boolean result = directedGraph.removeNode("node1");
        Assert.assertTrue(result);
    }

    @Test
    public void testRemoveNonExistingNode() {
        directedGraph.addNode("node1");
        boolean result = directedGraph.removeNode("node2");
        Assert.assertFalse(result);
    }

    @Test
    public void testRemoveEdgeFromExistingNode() {
        directedGraph.addNode("node1");
        boolean result = directedGraph.removeEdge("node1", "node2");
        Assert.assertTrue(result);
    }

    @Test
    public void testRemoveEdgeFromNonExistingNode() {

        boolean result = directedGraph.removeEdge("node1", "node2");
        Assert.assertFalse(result);
    }
}
