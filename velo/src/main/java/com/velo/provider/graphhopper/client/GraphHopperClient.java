package com.velo.provider.graphhopper.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.velo.provider.graphhopper.dto.GraphHopperResponse;

@Component
public class GraphHopperClient {
    private final RestClient restClient;
    private final String apiKey;
    private final String defaultProfile;

    public GraphHopperClient(
            RestClient graphHopperRestClient,
            @Value("${graphhopper.api-key}") String apiKey,
            @Value("${graphhopper.profile:car}") String defaultProfile) {

        this.restClient = graphHopperRestClient;
        this.apiKey = apiKey;
        this.defaultProfile = defaultProfile;
    }

    public GraphHopperResponse fetchRoute(
            String from,
            String to) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/route")
                    .queryParam("point", from)
                    .queryParam("point", to)
                    .queryParam("profile", defaultProfile)
                    .queryParam("key", apiKey)
                    .build())
                .retrieve()
                .body(GraphHopperResponse.class);
    }
}
