package com.example.amqp.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Value("%{spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
}
