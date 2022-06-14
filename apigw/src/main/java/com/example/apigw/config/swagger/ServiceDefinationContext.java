package com.example.apigw.config.swagger;

import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ServiceDefinationContext {

    private final ConcurrentHashMap<String, String> swaggrResources;

    public ServiceDefinationContext() {
        this.swaggrResources = new ConcurrentHashMap<>();
    }

    public void addServiceDefinition(String name, String content) {
        swaggrResources.put(name, content);
    }


    public List<SwaggerResource> getSwaggerDefinitions() {

        return swaggrResources.entrySet().stream().map(service -> {
            SwaggerResource resource = new SwaggerResource();
            resource.setName(service.getKey());
            resource.setUrl("/service/"+service.getKey());
            resource.setSwaggerVersion("2.0");
            return resource;
        }).collect(Collectors.toList());
    }

    public String getServiceDefinition(String serviceName) {
        return swaggrResources.get(serviceName);
    }
}
