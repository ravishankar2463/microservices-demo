package com.example.customer;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.amqp.queues.enums.Exchange;
import com.example.amqp.queues.enums.Queue;
import com.example.amqp.queues.enums.RoutingKeys;
import com.example.clients.product.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private static int id = 0;

    private final ProductClient productClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @GetMapping
    public ResponseEntity<String> getAllCustomers(){
        String response = "Get all customers was called from Customer Microservice";
        log.info(response);

        String productsResponse = productClient.getProducts().getBody();
        log.info(productsResponse);

        response = response.concat("\n"+productsResponse);

        id++;
        rabbitMQMessageProducer.publish(
                "customer" + id,
                Exchange.InternalExchange.name(),
                RoutingKeys.InternalNotificationRoutingKey.name()
        );

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}
