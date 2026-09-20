package com.velo.route.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.velo.route.dto.RouteDTO;
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

    @GetMapping("/route")
    public ResponseEntity<RouteDTO> getRoute(@RequestParam String from, @RequestParam  String to){
        RouteDTO route = routeMapper.toDTO(routeService.getRoute(from,to));
        return ResponseEntity.ok(route);
    }
}
