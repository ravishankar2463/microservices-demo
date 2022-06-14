package com.example.apigw.controller.swagger;

import com.example.apigw.config.swagger.ServiceDefinationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceDefinitionController {
    @Autowired
    private ServiceDefinationContext context;

    @GetMapping("/service/{serviceName}")
    public String getServiceDefinition(@PathVariable("serviceName") String serviceName) {
        return context.getServiceDefinition(serviceName);
    }
}
