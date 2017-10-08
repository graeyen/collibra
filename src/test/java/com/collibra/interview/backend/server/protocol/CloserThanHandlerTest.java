package com.collibra.interview.backend.server.protocol;

import com.collibra.interview.backend.server.model.DirectedGraph;
import com.collibra.interview.backend.server.protocol.handlers.CloserThanHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CloserThanHandlerTest {

    private CloserThanHandler closerThanHandler;
    private DirectedGraph directedGraph;
    private SessionContext sessionContext = new SessionContext(System.currentTimeMillis());

    @Before
    public void setup() {
        closerThanHandler = new CloserThanHandler();
        directedGraph = CloserThanHandler.getGraph();
    }

    @Test
    public void testHandle() {
        directedGraph.addNode("node1");
        directedGraph.addNode("node2");
        directedGraph.addNode("node3");

        directedGraph.addEdge("node1", "node3", 2);
        directedGraph.addEdge("node1", "node2", 3);

        String result = closerThanHandler.handle("CLOSER THAN 16 node1" ,sessionContext);

        Assert.assertEquals("node2,node3", result);
    }
}
