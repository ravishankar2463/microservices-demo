package com.example.customer;

import com.example.clients.product.ProductClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final ProductClient productClient;

    @Autowired
    private final KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping
    public ResponseEntity<String> getAllCustomers(){
        String response = "Get all customers was called from Customer Microservice";
        log.info(response);

        String productsResponse = productClient.getProducts().getBody();
        log.info(productsResponse);

        response = response.concat("\n"+productsResponse);

        kafkaTemplate.send("user",response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
