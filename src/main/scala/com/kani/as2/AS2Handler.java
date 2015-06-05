package com.kani.as2;

import com.sun.net.httpserver.HttpExchange;

/**
 * Created by kanishka on 5/18/15.
 */
public class AS2Handler {
    public void handle(HttpExchange httpExchange){
        AS2MessageEntity as2MessageEntity;
    }

    private AS2MessageEntity createIncommingMessage(HttpExchange httpExchange){
        AS2MessageEntity as2MessageEntity = new AS2MessageEntity();
        as2MessageEntity.setSourceIp(httpExchange.getRemoteAddress().getAddress());
        as2MessageEntity.setSourcePort(httpExchange.getRemoteAddress().getPort());
        as2MessageEntity.setDestinationIp(httpExchange.getLocalAddress().getAddress());
        as2MessageEntity.setDestinationPort(httpExchange.getLocalAddress().getPort());
        return as2MessageEntity;
    }
}
