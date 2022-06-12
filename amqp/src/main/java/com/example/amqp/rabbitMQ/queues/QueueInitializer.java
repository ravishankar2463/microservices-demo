package com.example.amqp.rabbitMQ.queues;

import com.example.amqp.rabbitMQ.queues.enums.Exchange;
import com.example.amqp.rabbitMQ.queues.enums.Queue;
import com.example.amqp.rabbitMQ.queues.enums.RoutingKeys;
import com.example.amqp.rabbitMQ.queues.exceptions.NoExchangeIsMappedToQueueException;
import com.example.amqp.rabbitMQ.queues.exceptions.NoRoutingKeyIsMappedToExchangeException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class QueueInitializer {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public void initializeQueues() throws NoRoutingKeyIsMappedToExchangeException, NoExchangeIsMappedToQueueException {
        List<Queue> queueList = Arrays.asList(Queue.values());

        if(!CollectionUtils.isEmpty(queueList)){
            for(Queue queue: queueList) {
                String queueName = queue.name();
                org.springframework.amqp.core.Queue rabbitQueue = generateNewQueue(queueName);
                if(!CollectionUtils.isEmpty(queue.getBindings())) {
                    Map<Exchange, RoutingKeys> exchanges = queue.getBindings();
                    for (Exchange exchange : exchanges.keySet()) {
                        TopicExchange rabbitExchange = generateNewTopicExchange(exchange.name());
                        String routingKey = exchanges.get(exchange).name();
                        generateNewBindings(rabbitQueue, rabbitExchange, routingKey);
                    }
                }
            }
        }
    }

    private void generateNewBindings(org.springframework.amqp.core.Queue queue, TopicExchange exchange, String routingKey) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    private TopicExchange generateNewTopicExchange(String exchangeName) {
        TopicExchange exchange = new TopicExchange(exchangeName);
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    private org.springframework.amqp.core.Queue generateNewQueue(String queueName) {
        org.springframework.amqp.core.Queue queue = new org.springframework.amqp.core.Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
}
