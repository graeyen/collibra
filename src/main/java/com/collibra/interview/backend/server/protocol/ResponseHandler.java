package com.collibra.interview.backend.server.protocol;

/**
 * Created by ger on 29/09/2017.
 */
public interface ResponseHandler {

    String handle(String response, SessionContext sessionContext);
}
