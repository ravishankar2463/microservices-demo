package com.example.amqp.rabbitMQ.queues.exceptions;

public class NoRoutingKeyIsMappedToExchangeException extends Exception{
    public NoRoutingKeyIsMappedToExchangeException(String message) {
        super(message);
    }
}
