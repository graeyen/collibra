package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 *
 */
public class GreetClientResponseHandler implements ResponseHandler {

    @Override
    public String handle(String response, SessionContext sessionContext) {
        String name = response.substring(8, response.length());
        sessionContext.clientName = name;

        return "HI " + name;

    }
}
