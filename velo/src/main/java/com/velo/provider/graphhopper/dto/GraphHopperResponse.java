package com.velo.provider.graphhopper.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GraphHopperResponse(
    List<Path> paths

) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Path(
        double distance,
        long time,
        double ascend,
        double descend,
        @JsonProperty("points") String polyline
    ) {}
}
