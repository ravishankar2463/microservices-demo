package com.example.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(
        scanBasePackages = {
                "com.example.notification",
                "com.example.amqp"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }
}

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/notification")
class NotificationController{
    @GetMapping(path = "/{customerName}")
    ResponseEntity<String> sendNotification(@PathVariable("customerName") String customerName){
        String response = "sendNotification was called from NotificationController "+customerName;
        log.info(response);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
