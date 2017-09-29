package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public class EndConversationHandler {

    public String handle(SessionContext sessionContext) {
        return "BYE " + sessionContext.clientName + ", WE SPOKE FOR " + (System.currentTimeMillis() - sessionContext.startTime) + " MS";
    }
}
