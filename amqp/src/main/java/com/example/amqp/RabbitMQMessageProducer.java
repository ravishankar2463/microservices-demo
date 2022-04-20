package com.example.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payLoad,String exchange, String routingKey){
        log.info(String.format("Publishing to %s using routing key %s. Payload: %s",exchange,routingKey,payLoad));
        amqpTemplate.convertAndSend(exchange,routingKey, payLoad);
        log.info(String.format("Published to %s.",exchange));
    }
}
