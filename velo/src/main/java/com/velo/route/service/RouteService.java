package com.velo.route.service;

import org.springframework.stereotype.Service;

import com.velo.route.domain.Coordinate;
import com.velo.route.domain.Route;

@Service
public class RouteService {
    private final RouteProvider routeProvider;

    public RouteService(RouteProvider routeProvider){
        this.routeProvider = routeProvider;
    }

    public Route getRoute(Coordinate from, Coordinate to){
        Route route = routeProvider.calculateRoute(from, to);
        return route;
    }
}
