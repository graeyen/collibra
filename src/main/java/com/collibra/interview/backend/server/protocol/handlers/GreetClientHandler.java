package com.collibra.interview.backend.server.protocol.handlers;

import com.collibra.interview.backend.server.protocol.ClientMessages;
import com.collibra.interview.backend.server.protocol.SessionContext;

/**
 * Created by ger on 29/09/2017.
 *
 */
public class GreetClientHandler implements ConversationHandler {

    @Override
    public boolean appliesTo(String inputMessage) {
        return inputMessage.startsWith(ClientMessages.GREETING);
    }

    @Override
    public String handle(String inputMessage, SessionContext sessionContext) {
        String name = inputMessage.substring(8, inputMessage.length());
        sessionContext.clientName = name;

        return "HI " + name;

    }
}
