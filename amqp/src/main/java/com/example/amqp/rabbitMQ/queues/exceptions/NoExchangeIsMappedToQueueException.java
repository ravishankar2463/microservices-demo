package com.example.amqp.rabbitMQ.queues.exceptions;

public class NoExchangeIsMappedToQueueException extends Exception{
    public NoExchangeIsMappedToQueueException(String message) {
        super(message);
    }
}
