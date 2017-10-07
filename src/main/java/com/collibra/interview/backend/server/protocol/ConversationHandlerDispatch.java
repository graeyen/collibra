package com.collibra.interview.backend.server.protocol;

import java.util.ArrayList;
import java.util.List;

public class ConversationHandlerDispatch {

    private List<ConversationHandler> conversationHandlers = new ArrayList();

    public ConversationHandlerDispatch() {
        conversationHandlers.add(new GreetClientHandler());
        conversationHandlers.add(new AddNodeHandler());
        conversationHandlers.add(new AddEdgeHandler());
        conversationHandlers.add(new RemoveNodeHandler());
        conversationHandlers.add(new RemoveEdgeHandler());
        conversationHandlers.add(new EndConversationHandler());
        conversationHandlers.add(new ShortestPathHandler());
        conversationHandlers.add(new CloserThanHandler());

        // should always be the latest of the list
        conversationHandlers.add(new UnkownConversationHandler());
    }

    public String dispatch(String inputMessage, SessionContext sessionContext) {

        for(ConversationHandler conversationHandler : conversationHandlers) {
            if(conversationHandler.appliesTo(inputMessage)) {
                return conversationHandler.handle(inputMessage, sessionContext);
            }

        }
        throw new RuntimeException("Default handler not configured.");
    }
}
