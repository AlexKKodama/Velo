package com.velo.route.dto;

import com.velo.route.domain.Coordinate;

public record  RouteRequest(
    Coordinate from,
    Coordinate to
) {}
