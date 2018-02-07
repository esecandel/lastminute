package com.lastminute.model;

import java.time.OffsetDateTime;
/**
 * FlightSearch entity
 * @author Sergio Candel
 *
 */
public class FlightSearch {


    private String originAirport;
    private String destinationAirport;
    private int passengers;
    private OffsetDateTime flightDate;

    public FlightSearch(String originAirport, String destinationAirport, int passengers, OffsetDateTime flightDate) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.passengers = passengers;
        this.flightDate = flightDate;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public OffsetDateTime getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(OffsetDateTime flightDate) {
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return "FlightSearch{" +
                "originAirport='" + originAirport + '\'' +
                ", destinationAirport='" + destinationAirport + '\'' +
                ", passengers=" + passengers +
                ", flightDate=" + flightDate +
                '}';
    }
}
