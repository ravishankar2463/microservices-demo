package com.example.amqp.queues.exceptions;

public class NoRoutingKeyIsMappedToExchangeException extends Exception{
    public NoRoutingKeyIsMappedToExchangeException(String message) {
        super(message);
    }
}
