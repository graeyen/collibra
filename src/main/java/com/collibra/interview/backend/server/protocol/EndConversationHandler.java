package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public class EndConversationHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.GOODBYE);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        return "BYE " + sessionContext.clientName + ", WE SPOKE FOR " + (System.currentTimeMillis() - sessionContext.startTime) + " MS";
    }
}
