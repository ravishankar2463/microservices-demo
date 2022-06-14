package com.example.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(
        scanBasePackages = {
                "com.example.common",
                "com.example.notification",
                "com.example.amqp"
        }
)
@EnableEurekaClient
@EnableSwagger2
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.notification"))
                .build()
                .apiInfo( new ApiInfo("Notification Demo Microservice",
                        "Sample API for demo",
                        "1.0",
                        "",
                        "",
                        "",
                        ""));
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
