package com.lastminute.service;

import com.lastminute.model.Route;

import java.util.List;
import java.util.stream.Collectors;

import static com.lastminute.CsvFiles.readAllRecords;


public class RouteService {

    private static List<Route> routes;

    private RouteService(){
    }

    public static void init(String filename) {
        List<List<String>> prices = readAllRecords(filename);

        routes = prices.stream().map(e -> new Route(e.get(0), e.get(1), e.get(2)))
                .collect(Collectors.toList());
    }

    public static List<Route> searchRoutesByOriginAndDestination(String origin, String destination) {
        return routes.stream()
                .filter(r -> origin.equals(r.getOriginAirport()) && destination.equals(r.getDestinationAirport()))
                .collect(Collectors.toList());
    }
}
