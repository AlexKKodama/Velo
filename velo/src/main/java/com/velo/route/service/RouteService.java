package com.velo.route.service;

import org.springframework.stereotype.Service;

import com.velo.route.domain.Route;

@Service
public class RouteService {
    private final RoutingProvider routingProvider;

    public RouteService(RoutingProvider routingProvider){
        this.routingProvider = routingProvider;
    }

    public Route getRoute(double from, double to){
        Route route = routingProvider.calculateRoute(from, to);
        return route;
    }
}
