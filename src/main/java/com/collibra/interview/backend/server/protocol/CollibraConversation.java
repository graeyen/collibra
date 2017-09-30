package com.collibra.interview.backend.server.protocol;

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

    public String handleResponse(String response) {
        if (response.startsWith(("HI, I'M"))) {
            return new ClientGreetResponseHandler().handle(response, sessionContext);
        } else {
            return new DefaultResponseHandler().handle(response, sessionContext);
        }
    }
}
