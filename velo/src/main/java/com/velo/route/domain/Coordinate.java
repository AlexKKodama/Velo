package com.velo.route.domain;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record Coordinate (
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    double lat,

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    double lon
){}
