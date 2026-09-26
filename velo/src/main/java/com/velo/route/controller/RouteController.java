package com.velo.route.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.velo.route.dto.RouteDTO;
import com.velo.route.dto.RouteRequest;
import com.velo.route.mapper.RouteMapper;
import com.velo.route.service.RouteService;

@RestController
public class RouteController {
    private final RouteService routeService;
    private final RouteMapper routeMapper;

    public RouteController(RouteService routeService, RouteMapper routeMapper){
        this.routeService = routeService;
        this.routeMapper = routeMapper;
    }

    @PostMapping("/route")
    public ResponseEntity<RouteDTO> getRoute(@RequestBody RouteRequest request){
        RouteDTO route = routeMapper.toDTO(routeService.getRoute(request.from(), request.to()));
        return ResponseEntity.ok(route);
    }
}
