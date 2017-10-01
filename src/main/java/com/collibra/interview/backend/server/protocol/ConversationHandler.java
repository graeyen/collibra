package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public interface ConversationHandler {

    boolean appliesTo(String inputMessage);

    String handle(String inputMessage, SessionContext sessionContext);
}
