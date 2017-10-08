package com.collibra.interview.backend.server;

import ch.qos.logback.classic.Level;
import com.collibra.interview.backend.server.core.CollibraServer;
import org.slf4j.LoggerFactory;

/**
 * Starts the collibra server.
 */
public class CollibraBootstrap {

    private static final int DEFAULT_PORT = 50000;

    public static void main(String[] args)  {
        if(args.length > 0) {
            ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
            Level newLevel = Level.valueOf(args[0]);
            root.setLevel(newLevel);
        }
        new CollibraServer().start(DEFAULT_PORT);
    }
}
