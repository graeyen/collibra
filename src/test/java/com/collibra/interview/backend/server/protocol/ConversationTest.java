package com.collibra.interview.backend.server.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConversationTest {

    private Conversation conversation;

    @Before
    public void setup() {
        conversation = Conversation.create();
        conversation.start();
    }

    @Test
    public void testAnswerGreeting() {
       String result = conversation.answer(ClientMessages.GREETING  + " 4456");
        Assert.assertEquals("HI 4456", result);
    }

    @Test
    public void testAnswerAddNode() {
        String result = conversation.answer(ClientMessages.ADD_NODE  + " NODE1");
        Assert.assertEquals("NODE ADDED", result);
    }

    @Test
    public void testAnswerAddEdge() {
        String result = conversation.answer(ClientMessages.ADD_EDGE  + " NODE1 NODE2 5");
        Assert.assertEquals("ERROR: NODE NOT FOUND", result);
    }

    @Test
    public void testAnswerRemoveNode() {
        String result = conversation.answer(ClientMessages.REMOVE_NODE  + " NODE1");
        Assert.assertEquals("ERROR: NODE NOT FOUND", result);
    }

    @Test
    public void testAnswerRemoveEdge() {
        String result = conversation.answer(ClientMessages.REMOVE_EDGE  + " NODE1 NODE2");
        Assert.assertEquals("ERROR: NODE NOT FOUND", result);
    }

    @Test
    public void testAnswerGoodbye() {
        String result = conversation.answer(ClientMessages.GOODBYE);
        Assert.assertTrue(result.startsWith("BYE null, WE SPOKE FOR"));
    }

    @Test
    public void testAnswerUnknown() {
        String result = conversation.answer("BLA");
        Assert.assertEquals("SORRY, I DIDN'T UNDERSTAND THAT", result);
    }
}
