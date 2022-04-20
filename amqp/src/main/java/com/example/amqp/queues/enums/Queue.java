package com.example.amqp.queues.enums;

import lombok.experimental.FieldNameConstants;

import java.util.HashMap;
import java.util.Map;

@FieldNameConstants(innerTypeName = "NAME",onlyExplicitlyIncluded = true)
public enum Queue {
    @FieldNameConstants.Include NotificationQueue(notificationQueueBindingMap());

    private final Map<Exchange,RoutingKeys> bindings;

    Queue(Map<Exchange, RoutingKeys> bindings) {
        this.bindings = bindings;
    }

    public Map<Exchange, RoutingKeys> getBindings() {
        return bindings;
    }

    private static Map<Exchange, RoutingKeys> notificationQueueBindingMap() {
        Map<Exchange,RoutingKeys> notificationQueueBindingMap = new HashMap<>();
        notificationQueueBindingMap.put(Exchange.InternalExchange,RoutingKeys.InternalNotificationRoutingKey);
        return notificationQueueBindingMap;
    }
}

