package com.collibra.interview.backend.server;

import com.collibra.interview.backend.server.core.CollibraServer;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraBootstrap {

    private static final int DEFAULT_PORT = 50000;

    public static void main(String[] args)  {

        CollibraServer collibraServer = new CollibraServer();
        collibraServer.start(DEFAULT_PORT);
    }
}
