package com.collibra.interview.backend.server.algorithm;

import com.collibra.interview.backend.server.algorithm.PathFinder;
import com.collibra.interview.backend.server.model.DirectedGraph;
import com.collibra.interview.backend.server.model.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathFinderTest {

    private DirectedGraph directedGraph;
    private PathFinder pathFinder;

    @Before
    public void setup() {
        directedGraph = new DirectedGraph();
        pathFinder = new PathFinder();
    }


    @Test
    public void testFindShortestPathNoPath() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");

        int result = pathFinder.findShortestPath(getNode("node1"), getNode("node2"));
        Assert.assertEquals(Integer.MAX_VALUE, result);

    }

    @Test
    public void testFindShortestPathOnePathTwoNodes() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addEdge("node1", "node2", 3);
        int result = pathFinder.findShortestPath(getNode("node1"), getNode("node2"));
        Assert.assertEquals(3, result);

    }


    @Test
    public void testFindShortestPathOnePathThreeNodes() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);

        int result = pathFinder.findShortestPath(getNode("node1"), getNode("node3"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node4"));
        Assert.assertEquals(9, result);

    }

    @Test
    public void testFindShortestPathOnePathFourNodesNoTarget() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 2);
        directedGraph.addEdge("node3", "node4", 4);

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node5"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node4"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node4"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node5"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node5"));
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

        int result = pathFinder.findShortestPath(getNode("node1"),getNode( "node7"));
        Assert.assertEquals(17, result);

    }

    @Test
    public void testLongPath() {
        for(int i=1; i<=1000;i++) {
            directedGraph.addNode("Node" +i);
        }
        for(int i=1; i<=999; i++) {
            directedGraph.addEdge("Node"+i, "Node" + (i+1), 2);
        }
        Assert.assertEquals(1998, pathFinder.findShortestPath(getNode("Node1"),getNode( "Node1000")));
    }

    @Test
    public void testLongPathBranches() {
        String subNodeNameConnector = "";
        String subNodeNameLoopback1 = "";
        String subNodeNameLoopback2 = "";

        for(int i=1; i<=1000;i++) {
            String nodeName = "Node" +i;
            Assert.assertTrue(directedGraph.addNode(nodeName));

            if(i>1) {
                Assert.assertTrue(directedGraph.addEdge(subNodeNameConnector, nodeName, 3));
            }
            if(i>2) {
                Assert.assertTrue(directedGraph.addEdge(subNodeNameLoopback1, subNodeNameLoopback2, 3));
            }

            for(int j=1; j<8;j++) {
                subNodeNameConnector = nodeName + "-" + "node" + j;
                if (j ==3) {
                    subNodeNameLoopback2 = subNodeNameLoopback1;
                    subNodeNameLoopback1 = subNodeNameConnector;
                }
                Assert.assertTrue(directedGraph.addNode(subNodeNameConnector));
                Assert.assertTrue(directedGraph.addEdge(nodeName, subNodeNameConnector, 2));
            }
        }

        Assert.assertEquals(4997, pathFinder.findShortestPath(getNode("Node1"),getNode( "Node1000-node7")));
    }

    @Test
    public void testBranches() {
        for(int i=1; i<=21; i++) {
            directedGraph.addNode("node" + i);
        }

        directedGraph.addEdge("node1", "node2", 2);
        directedGraph.addEdge("node2", "node3", 4);
        directedGraph.addEdge("node2", "node7", 8);
        directedGraph.addEdge("node2", "node6", 3);
        directedGraph.addEdge("node1", "node4", 5);
        directedGraph.addEdge("node1", "node8", 6);
        directedGraph.addEdge("node8", "node9", 3);
        directedGraph.addEdge("node8", "node10", 4);
        directedGraph.addEdge("node8", "node11", 7);
        directedGraph.addEdge("node4", "node5", 3);
        directedGraph.addEdge("node4", "node12", 4);
        directedGraph.addEdge("node11", "node17", 9);
        directedGraph.addEdge("node12", "node13", 2);
        directedGraph.addEdge("node12", "node14", 5);
        directedGraph.addEdge("node12", "node15", 4);
        directedGraph.addEdge("node12", "node16", 6);
        directedGraph.addEdge("node12", "node17", 3);
        directedGraph.addEdge("node16", "node18", 6);
        directedGraph.addEdge("node16", "node19", 4);
        directedGraph.addEdge("node18", "node20", 8);


        Assert.assertEquals(29, pathFinder.findShortestPath(getNode("node1"), getNode("node20")));
        Assert.assertEquals(12, pathFinder.findShortestPath(getNode("node1"), getNode("node17")));
        Assert.assertEquals(Integer.MAX_VALUE, pathFinder.findShortestPath(getNode("node4"), getNode("node11")));
        Assert.assertEquals(Integer.MAX_VALUE, pathFinder.findShortestPath(getNode("node1"), getNode("node21")));


    }

    @Test
    public void testCirclePattern() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node1", 3);

        Assert.assertEquals(Integer.MAX_VALUE, pathFinder.findShortestPath(getNode("node1"),getNode( "node4")));

    }

    @Test
    public void testCirclePatternToFirstNodeWithOneBranch() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 3);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 3);
        directedGraph.addEdge("node4", "node1", 3);
        directedGraph.addEdge("node2", "node5", 8);

        Assert.assertEquals(11, pathFinder.findShortestPath(getNode("node1"),getNode( "node5")));

    }

    @Test
    public void testCirclePatternToSecondNodeWithOneBranch() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");

        directedGraph.addEdge("node1", "node2", 2);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 3);
        directedGraph.addEdge("node3", "node5", 7);
        directedGraph.addEdge("node4", "node2", 3);


        Assert.assertEquals(12, pathFinder.findShortestPath(getNode("node1"),getNode( "node5")));

    }

    @Test
    public void testCirclePatternToSecondNodeWithOneTwoBranches() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");
        directedGraph.addNode("node6");
        directedGraph.addNode("node7");
        directedGraph.addNode("node8");

        directedGraph.addEdge("node1", "node2", 2);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 5);
        directedGraph.addEdge("node3", "node7", 7);
        directedGraph.addEdge("node4", "node5", 6);
        directedGraph.addEdge("node5", "node6", 3);
        directedGraph.addEdge("node5", "node8", 3);
        directedGraph.addEdge("node6", "node2", 3);


        Assert.assertEquals(19, pathFinder.findShortestPath(getNode("node1"),getNode( "node8")));

    }

    @Test
    public void testCirclePatternToSecondNodeWithOneTwoBranchesLoopInSecondBranch() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");
        directedGraph.addNode("node6");
        directedGraph.addNode("node7");
        directedGraph.addNode("node8");
        directedGraph.addNode("node9");
        directedGraph.addNode("node10");

        directedGraph.addEdge("node1", "node2", 2);
        directedGraph.addEdge("node2", "node3", 3);
        directedGraph.addEdge("node3", "node4", 5);
        directedGraph.addEdge("node3", "node7", 7);
        directedGraph.addEdge("node4", "node5", 6);
        directedGraph.addEdge("node5", "node6", 3);
        directedGraph.addEdge("node5", "node8", 3);
        directedGraph.addEdge("node5", "node10", 4);
        directedGraph.addEdge("node8", "node2", 3);


        Assert.assertEquals(Integer.MAX_VALUE, pathFinder.findShortestPath(getNode("node1"), getNode("node9")));
        Assert.assertEquals(20, pathFinder.findShortestPath(getNode("node1"), getNode("node10")));

    }

    @Test
    public void testSameNode() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");
        directedGraph.addNode("node4");
        directedGraph.addNode("node5");
        directedGraph.addNode("node6");
        directedGraph.addNode("node7");
        directedGraph.addNode("node8");
        directedGraph.addNode("node9");
        directedGraph.addNode("node10");


        directedGraph.addEdge("node9", "node2", 2);
        directedGraph.addEdge("node9", "node3", 3);
        directedGraph.addEdge("node9", "node4", 5);
        directedGraph.addEdge("node9", "node7", 7);
        Assert.assertEquals(0, pathFinder.findShortestPath(getNode("node9"),getNode("node9")));
    }

    private Node getNode(String name) {
        return directedGraph.getNode(name);
    }
}
