package com.example.notification.rabbitmq;

import com.example.amqp.rabbitMQ.queues.enums.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

    public void consumer(Object s){
      log.info(s.toString());
    }

}
