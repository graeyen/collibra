package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public class UnkownConversationHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return true;
    }

    @Override
    public String handle(String response, SessionContext sessionContext) {
        return "SORRY, I DIDN'T UNDERSTAND THAT";
    }
}
