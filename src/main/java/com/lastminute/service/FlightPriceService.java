package com.lastminute.service;

import com.lastminute.model.FlightPrice;

import java.util.List;
import java.util.stream.Collectors;

import static com.lastminute.CsvFiles.readAllRecords;


public class FlightPriceService {

    private static List<FlightPrice> flightPrices;

    private FlightPriceService(){
    }

    public static void init(String filename) {
        List<List<String>> pricesInString = readAllRecords(filename);

        flightPrices = pricesInString.stream().map(e -> new FlightPrice(e.get(0), Double.valueOf(e.get(1))))
                .collect(Collectors.toList());

    }

    public static FlightPrice findFlightPriceByCode(String flightCode) {
        return flightPrices.stream()
                .filter(p -> flightCode.equals(p.getCode()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
