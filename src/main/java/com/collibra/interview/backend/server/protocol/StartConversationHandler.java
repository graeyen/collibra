package com.collibra.interview.backend.server.protocol;

import java.util.function.Supplier;

/**
 * Created by ger on 29/09/2017.
 */
public class StartConversationHandler {

    private Supplier<String> sessionIdSupplier = () -> java.util.UUID.randomUUID().toString();

    public String handle() {
       return "HI, I'M " +  sessionIdSupplier.get();
    }
}
