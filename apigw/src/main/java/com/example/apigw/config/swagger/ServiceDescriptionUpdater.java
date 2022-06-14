package com.example.apigw.config.swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class ServiceDescriptionUpdater {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate template;

    public ServiceDescriptionUpdater() {
        this.template = new RestTemplate();
    }

    @Autowired
    private ServiceDefinationContext definitionContext;

    @Scheduled(fixedDelayString = "${swagger.config.refreshrate}")
    public void refreshSwaggerConfigurations() {
        discoveryClient.getServices().stream().forEach(serviceId -> {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
            if (serviceInstances == null || serviceInstances.isEmpty()) {
                System.out.println("No instances available for service : {} "+ serviceId);
            }else {
                ServiceInstance instance = serviceInstances.get(0);
                String swaggerURL = instance.getUri()+"/v2/api-docs/";
                try {
                    Object jsonData = template.getForObject(swaggerURL, Object.class);
                    String content = getJSON(serviceId, jsonData);
                    definitionContext.addServiceDefinition(serviceId, content);
                    log.info("Swagger Documentation Added for Instance : "+ instance.getServiceId());
                }catch (HttpClientErrorException e){
                    log.info("No Swagger API Documentation Found for Instance : "+ instance.getServiceId());
                }

            }});
    }

    public String getJSON(String serviceId, Object jsonData) {
        try {
            return new ObjectMapper().writeValueAsString(jsonData);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
