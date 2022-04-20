package com.example.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "PRODUCT",path = "/api/v1/products")
public interface ProductClient {

    @GetMapping
    ResponseEntity<String> getProducts();

}
