package com.lastminute.service;

import com.lastminute.model.FlightPrice;
import com.lastminute.model.FlightSearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearchService {

    private FlightSearchService(){
    }

    /**
     * Flight searching using origin and destination airport, in a date, by a number of passengers.
     *
     * @param originAirport
     * @param destinationAirport
     * @param passengers
     * @param flightDate
     * @return a list with flights and its prices.
     */
    public static List<FlightPrice> searchFlights(String originAirport, String destinationAirport, int passengers, OffsetDateTime flightDate) {
        return searchFlights(new FlightSearch(originAirport, destinationAirport, passengers, flightDate));
    }

    private static List<FlightPrice> searchFlights(FlightSearch flightSearch) {

        List<FlightPrice> flightPrices = RouteService.searchRoutesByOriginAndDestination(flightSearch.getOriginAirport(),
                flightSearch.getDestinationAirport())
                .stream()
                .map(f -> FlightPriceService.findFlightPriceByCode(f.getCode()))
                .collect(Collectors.toList());

        return flightPrices.stream()
                .map(r -> applyPercentage(r, flightSearch))
                .sorted(Comparator.comparing(FlightPrice::getPrice))
                .collect(Collectors.toList());
    }


    /**
     * Apply percentage to base price and multiply by number of passengers, setting a new price into flightPrice entity
     *
     * @param flightPrice
     * @param flightSearch
     * @return modified price into flightPrice entity
     */
    private static FlightPrice applyPercentage(FlightPrice flightPrice, FlightSearch flightSearch) {

        BigDecimal bd = BigDecimal.valueOf(flightSearch.getPassengers()
                * getPercentage(flightSearch.getFlightDate())
                * flightPrice.getPrice());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        flightPrice.setPrice(bd.doubleValue());

        return flightPrice;
    }

    /**
     * * Ticket price is based on the number of days to the flight departure and the base price of the flight:
     * <p>
     * | days prior to the departure date | % of the base price |
     * |----------------------------------|---------------------|
     * | more than 30 (i.e. >= 31)        | 80%                 |
     * | 30 - 16                          | 100%                |
     * | 15 - 3                           | 120%                |
     * | less that 3 (i.e. <= 2)          | 150%                |
     * <p>
     * The percentage will be multiply to base price
     *
     * @return percentage to apply to base price of flight, for example, if flight date is before to 30 days, return 0.8d
     * to apply a 20% to discount.
     */
    public static double getPercentage(OffsetDateTime flightDate) {

        OffsetDateTime now = OffsetDateTime.now();
        if (now.plus(30, ChronoUnit.DAYS).isBefore(flightDate)) {
            return 0.8d;
        } else if (now.plus(30, ChronoUnit.DAYS).isAfter(flightDate)
                && now.plus(16, ChronoUnit.DAYS).isBefore(flightDate)) {
            return 1d;
        } else if (now.plus(16, ChronoUnit.DAYS).isAfter(flightDate)
                && now.plus(3, ChronoUnit.DAYS).isBefore(flightDate)) {
            return 1.2d;
        } else if (now.plus(3, ChronoUnit.DAYS).isAfter(flightDate)) {
            return 1.5d;
        } else {
            return 1d;
        }
    }

}
