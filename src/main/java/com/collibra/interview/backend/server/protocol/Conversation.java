package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public class Conversation {

    private StartConversationHandler startConversationHandler = new StartConversationHandler();
    private SessionContext sessionContext;

    private Conversation() {
        // Use the factory method for creating a new instance
    }

    public static Conversation create() {
        return new Conversation();
    }

    public String start() {
        sessionContext = new SessionContext(System.currentTimeMillis());
        return startConversationHandler.handle();
    }

    public String end() {
        return new EndConversationHandler().handle(sessionContext);
    }

    public String answer(String inputMessage) {
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
            return end();
        }

        else {
            return new UnkownResponseHandler().handle(inputMessage, sessionContext);
        }
    }
}
