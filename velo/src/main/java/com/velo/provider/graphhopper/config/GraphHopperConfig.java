package com.velo.provider.graphhopper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class GraphHopperConfig {
    @Value("${graphhopper.base-url}")
    private String baseUrl;

    @Bean
    public RestClient graphHoppeRestClient(){
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Accept", "application/json")
            .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new RuntimeException("GraphHopper Client Error: " + response.getStatusCode());
            })
            .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
                throw new RuntimeException("GraphHopper Remote Server Error: " + response.getStatusCode());
            })
            .build();
    }
}
