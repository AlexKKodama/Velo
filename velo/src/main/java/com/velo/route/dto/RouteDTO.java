package com.velo.route.dto;

public record RouteDTO(
    String polyline,
    double distance,
    long time
) {}
