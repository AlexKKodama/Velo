package com.velo.route.service;

import com.velo.route.domain.Route;

public interface RouteProvider {
    Route calculateRoute(String from, String to);
}
