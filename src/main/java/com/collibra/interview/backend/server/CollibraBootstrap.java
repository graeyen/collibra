package com.collibra.interview.backend.server;

import com.collibra.interview.backend.server.core.CollibraServer;

/**
 * Created by ger on 29/09/2017.
 */
public class CollibraBootstrap {

    public static void main(String[] args) throws Exception {

        CollibraServer collibraServer = new CollibraServer();
        collibraServer.start(50000);
    }
}
