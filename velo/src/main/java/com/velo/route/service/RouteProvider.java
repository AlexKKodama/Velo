package com.velo.route.service;

import com.velo.route.domain.Coordinate;
import com.velo.route.domain.Route;

public interface RouteProvider {
    Route calculateRoute(Coordinate from, Coordinate to);
}
