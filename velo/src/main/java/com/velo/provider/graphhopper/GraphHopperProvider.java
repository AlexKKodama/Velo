package com.velo.provider.graphhopper;

import org.springframework.stereotype.Component;

import com.velo.provider.graphhopper.client.GraphHopperClient;
import com.velo.provider.graphhopper.dto.GraphHopperResponse;
import com.velo.provider.graphhopper.mapper.GraphHopperMapper;
import com.velo.route.domain.Route;
import com.velo.route.service.RouteProvider;

@Component
public class GraphHopperProvider implements RouteProvider{
    private final GraphHopperClient client;
    private final GraphHopperMapper mapper;

    public GraphHopperProvider(GraphHopperClient client, GraphHopperMapper mapper){
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Route calculateRoute(String from, String to){
        GraphHopperResponse response = client.fetchRoute(from, to);

        return mapper.toDomain(response);
    }
}
