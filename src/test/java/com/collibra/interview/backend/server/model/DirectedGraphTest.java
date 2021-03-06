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
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 5);
        boolean result = directedGraph.removeEdge("node1", "node2");
        Assert.assertTrue(result);
    }

    @Test
    public void testRemoveEdgeFromNonExistingNode() {

        boolean result = directedGraph.removeEdge("node1", "node2");
        Assert.assertFalse(result);
    }

    @Test
    public void testFindShortestPathNoPath() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");

        int result = directedGraph.findShortestPath("node1", "node2");
        Assert.assertEquals(Integer.MAX_VALUE, result);

    }

    @Test
    public void testFindShortestPathWhen2NodesSingleEdge() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 5);
        int result = directedGraph.findShortestPath("node1", "node2");
        Assert.assertEquals(5, result);

    }

    @Test
    public void testFindShortestPathWhen2NodesDoubleEdge() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 5);
        directedGraph.addEdge("node1", "node2", 2);
        int result = directedGraph.findShortestPath("node1", "node2");
        Assert.assertEquals(2, result);

    }

    @Test
    public void testFindShortestPathWhen3NodesSingleEdge() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addEdge("node1", "node2", 5);
        directedGraph.addEdge("node2", "node3", 3);
        int result = directedGraph.findShortestPath("node1", "node3");
        Assert.assertEquals(8, result);

    }

    @Test
    public void testFindShortestPathWhen3NodesAnd1DoubleEdge() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addEdge("node1", "node2", 5);
        directedGraph.addEdge("node2", "node3", 6);
        directedGraph.addEdge("node2", "node3", 4);
        int result = directedGraph.findShortestPath("node1", "node3");
        Assert.assertEquals(9, result);

    }

    @Test
    public void testFindShortestPathWhen4NodesSingleEdge() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addEdge("node1", "node2", 5);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 6);

        Assert.assertEquals(14, directedGraph.findShortestPath("node1", "node4"));
        Assert.assertEquals(9, directedGraph.findShortestPath("node2", "node4"));
        Assert.assertEquals(Integer.MAX_VALUE, directedGraph.findShortestPath("node3", "node1"));

    }

    @Test
    public void testFindShortestPathWhenLongerPathIsTheShortest() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");

        directedGraph.addEdge("node1", "node4", 50);
        directedGraph.addEdge("node1", "node2", 4);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 6);

        Assert.assertEquals(13, directedGraph.findShortestPath("node1", "node4"));


    }

    @Test
    public void testContent() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addEdge("node1", "node3" , 2);
        directedGraph.addEdge("node2", "node3" , 5);
        directedGraph.addEdge("node2", "node1" , 8);

        String result = directedGraph.toString();
        Assert.assertEquals("[[node1:(->node3+2)][node2:(->node3+5)(->node1+8)][node3:]]", result);

        directedGraph.removeEdge("node2", "node1");
        result = directedGraph.toString();
        Assert.assertEquals("[[node1:(->node3+2)][node2:(->node3+5)][node3:]]", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestPathTargetNodeNotExisting() {
        directedGraph.addNode("node1");
        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.findShortestPath("node1", "node2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindShortestPathStartNodeNotExisting() {
        directedGraph.addNode("node1");
        directedGraph.addEdge("node2", "node1", 3);
        directedGraph.findShortestPath("node2", "node1");
    }
}
