package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraProtocol {

    private StartConversationHandler startConversationHandler = new StartConversationHandler();

    public String start() {
        return startConversationHandler.handle();
    }

    public String end() {
        return new EndConversationHandler().handle()
    }

}
