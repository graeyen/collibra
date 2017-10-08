package com.collibra.interview.backend.server.protocol;

import com.collibra.interview.backend.server.protocol.handlers.ConversationHandlerDispatch;
import com.collibra.interview.backend.server.protocol.handlers.EndConversationHandler;
import com.collibra.interview.backend.server.protocol.handlers.StartConversationHandler;

/**
 * Created by ger on 29/09/2017.
 */
public class Conversation {

    private StartConversationHandler startConversationHandler = new StartConversationHandler();
    private ConversationHandlerDispatch dispatch = new ConversationHandlerDispatch();
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
        return new EndConversationHandler().handle("", sessionContext);
    }

    public String answer(String inputMessage) {
        return dispatch.dispatch(inputMessage, sessionContext);
    }
}
