package com.lastminute;

import com.lastminute.model.FlightPrice;
import com.lastminute.service.FlightPriceService;
import com.lastminute.service.FlightSearchService;
import com.lastminute.service.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceTest {

    private static final String FLIGHT_ROUTES_CSV = "flight-routes.csv";
    private static final String FLIGHT_PRICES_CSV = "flight-prices.csv";

    @Before
    public void setup(){
        RouteService.init(getClass().getClassLoader().getResource(FLIGHT_ROUTES_CSV).getPath());
        FlightPriceService.init(getClass().getClassLoader().getResource(FLIGHT_PRICES_CSV).getPath());
    }

    /*
     * 1 passenger, 31 days to the departure date, flying AMS -> FRA. Flights:
     * TK2372, 157.6 €
     * TK2659, 198.4 €
     * LH5909, 90.4 €
     */
    @Test
    public void flightSearchingTest_Example1(){
        OffsetDateTime flightDate = OffsetDateTime.now().plus(31, ChronoUnit.DAYS);
        List<FlightPrice> result = FlightSearchService.searchFlights("AMS", "FRA", 1, flightDate);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        //Results are sorted by price ASC
        Assert.assertEquals(new FlightPrice("LH5909", 90.4d), result.get(0));
        Assert.assertEquals(new FlightPrice("TK2372", 157.6d), result.get(1));
        Assert.assertEquals(new FlightPrice("TK2659", 198.4d), result.get(2));
    }

    /*
     * 3 passengers, 15 days to the departure date, flying LHR -> IST. Flights:
     * TK8891, 900 € (3 * (120% of 250))
     * LH1085, 532.8 € (3 * (120% of 148))
     */
    @Test
    public void flightSearchingTest_Example2(){
        OffsetDateTime flightDate = OffsetDateTime.now().plus(15, ChronoUnit.DAYS);
        List<FlightPrice> result = FlightSearchService.searchFlights("LHR", "IST", 3, flightDate);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        //Results are sorted by price ASC
        Assert.assertEquals(new FlightPrice("LH1085", 532.8d), result.get(0));
        Assert.assertEquals(new FlightPrice("TK8891", 900d), result.get(1));
    }

    /*
     * 2 passengers, 2 days to the departure date, flying BCN -> MAD. Flights:
     * IB2171, 777 € (2 * (150% of 259))
     * LH5496, 879 € (2 * (150% of 293))
     */
    @Test
    public void flightSearchingTest_Example3(){
        OffsetDateTime flightDate = OffsetDateTime.now().plus(2, ChronoUnit.DAYS);
        List<FlightPrice> result = FlightSearchService.searchFlights("BCN", "MAD", 2, flightDate);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        //Results are sorted by price ASC
        Assert.assertEquals(new FlightPrice("IB2171", 777d), result.get(0));
        Assert.assertEquals(new FlightPrice("LH5496", 879d), result.get(1));
    }

    /*
     * CDG -> FRA
     * no flights available
     */
    @Test
    public void flightSearchingTest_Example4(){
        OffsetDateTime flightDate = OffsetDateTime.now().plus(2, ChronoUnit.DAYS);
        List<FlightPrice> result = FlightSearchService.searchFlights("CDG", "FRA", 2, flightDate);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

}
