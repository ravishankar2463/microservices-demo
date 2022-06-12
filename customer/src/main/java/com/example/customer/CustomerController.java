package com.example.customer;

import com.example.amqp.rabbitMQ.RabbitMQMessageProducer;
import com.example.amqp.rabbitMQ.queues.enums.Exchange;
import com.example.amqp.rabbitMQ.queues.enums.RoutingKeys;
import com.example.clients.product.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final ProductClient productClient;

    @GetMapping
    public ResponseEntity<String> getAllCustomers(){
        String response = "Get all customers was called from Customer Microservice";
        log.info(response);

        String productsResponse = productClient.getProducts().getBody();
        log.info(productsResponse);

        response = response.concat("\n"+productsResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
