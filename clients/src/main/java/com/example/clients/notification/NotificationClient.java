package com.example.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "NOTIFICATION",path = "/api/v1/notification")
public interface NotificationClient {

    @GetMapping(path = "/{customerName}")
    ResponseEntity<String> sendNotification(@PathVariable("customerName") String customerName);
}
