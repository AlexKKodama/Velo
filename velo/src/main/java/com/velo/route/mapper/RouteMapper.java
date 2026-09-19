package com.velo.route.mapper;

import org.springframework.stereotype.Component;

import com.velo.route.domain.Route;
import com.velo.route.dto.RouteDTO;

@Component
public class RouteMapper {
    public RouteDTO toDTO(Route route){
        return new RouteDTO(
            route.polyline(),
            route.distance(),
            route.time()
        );
    }
}
