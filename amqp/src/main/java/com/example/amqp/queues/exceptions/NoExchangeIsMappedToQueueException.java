package com.example.amqp.queues.exceptions;

public class NoExchangeIsMappedToQueueException extends Exception{
    public NoExchangeIsMappedToQueueException(String message) {
        super(message);
    }
}
