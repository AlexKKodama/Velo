package com.velo.route.domain;

public record Route(
    String polyline,
    double distance,
    int time
) {}
