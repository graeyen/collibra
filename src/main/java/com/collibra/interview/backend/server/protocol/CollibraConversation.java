package com.collibra.interview.backend.server.protocol;

import com.collibra.interview.backend.server.model.DirectedGraph;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraConversation {

    private StartConversationHandler startConversationHandler = new StartConversationHandler();
    private SessionContext sessionContext;

    // Use the factory method for creating a new instance
    private CollibraConversation() {
        sessionContext = new SessionContext(System.currentTimeMillis());
    }

    public static CollibraConversation startNewConversation() {
        return new CollibraConversation();
    }

    public String createStartMessage() {
        return startConversationHandler.handle();
    }

    public String createEndMessage() {
        return new EndConversationHandler().handle(sessionContext);
    }

    public String createMessageForInput(String inputMessage) {
        if (inputMessage.startsWith(("HI, I'M"))) {
            return new GreetClientHandler().handle(inputMessage, sessionContext);
        } else if (inputMessage.startsWith("ADD NODE")) {
            return new AddNodeHandler().handle(inputMessage);
        } else if (inputMessage.startsWith("ADD EDGE")) {
            return new AddEdgeHandler().handle(inputMessage);
        } else if (inputMessage.startsWith("REMOVE NODE")) {
            return new RemoveNodeHandler().handle(inputMessage);
        } else if (inputMessage.startsWith("REMOVE EDGE")) {
            return new RemoveEdgeHandler().handle(inputMessage);
        } else if (inputMessage.startsWith("BYE MATE!")) {
            return createEndMessage();
        }

        else {
            return new UnkownResponseHandler().handle(inputMessage, sessionContext);
        }
    }
}
