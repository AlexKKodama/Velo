package com.velo.provider.graphhopper.mapper;

import org.springframework.stereotype.Component;

import com.velo.provider.graphhopper.dto.GraphHopperResponse;
import com.velo.route.domain.Route;

@Component
public class GraphHopperMapper {
    public Route toDomain(GraphHopperResponse response) {
    if (response == null || response.paths() == null || response.paths().isEmpty()) {
        throw new IllegalArgumentException("No route paths found in GraphHopper response");
    }

    // Get the primary path returned by GraphHopper
    GraphHopperResponse.Path path = response.paths().get(0); // Use getFirst() if on Java 21+

    return new Route(
        path.polyline(),
        path.distance(),
        path.time()
    );
}
}
