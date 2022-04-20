package com.example.apigw;

import com.example.amqp.queues.QueueInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.example.apigw",
        "com.example.amqp"
})
public class ApiGWApplication{

    @Autowired
    private QueueInitializer queueInitializer;

    public static void main(String[] args) {
        SpringApplication.run(ApiGWApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            queueInitializer.initializeQueues();
        };
    }
}

@RestController
class HealthCheckController{
    @GetMapping("/online")
    public ResponseEntity<String> shouldReturnOk(){
        return new ResponseEntity<String>("online", HttpStatus.OK);
    }
}