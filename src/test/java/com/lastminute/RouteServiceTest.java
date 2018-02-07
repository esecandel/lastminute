package com.lastminute;

import com.lastminute.model.Route;
import com.lastminute.service.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    private static final String FLIGHT_ROUTES_CSV = "flight-routes.csv";

    @Before
    public void setup(){
        RouteService.init(getClass().getClassLoader().getResource(FLIGHT_ROUTES_CSV).getPath());
    }


    @Test
    public void searchRoutesByOriginAndDestinationTest_AMStoFRA(){
        String origin = "AMS";
        String destination = "FRA";
        List<Route> result = RouteService.searchRoutesByOriginAndDestination(origin, destination);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(new Route(origin, destination,"LH5909")));
        Assert.assertTrue(result.contains(new Route(origin, destination,"TK2372")));
        Assert.assertTrue(result.contains(new Route(origin, destination,"TK2659")));
    }

    @Test
    public void searchRoutesByOriginAndDestinationTest_CPHtoFRA(){
        String origin = "CPH";
        String destination = "FRA";
        List<Route> result = RouteService.searchRoutesByOriginAndDestination(origin, destination);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(new Route(origin, destination,"IB2818")));
        Assert.assertTrue(result.contains(new Route(origin, destination,"LH1678")));
    }

    @Test
    public void searchRoutesByOriginAndDestinationTest_FRAtoLHR(){
        String origin = "FRA";
        String destination = "LHR";
        List<Route> result = RouteService.searchRoutesByOriginAndDestination(origin, destination);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(new Route(origin, destination,"BA8162")));
        Assert.assertTrue(result.contains(new Route(origin, destination,"IB9443")));
        Assert.assertTrue(result.contains(new Route(origin, destination,"TK3167")));
    }

    @Test
    public void searchRoutesByOriginAndDestinationTest_FRAtoXXX(){
        String origin = "FRA";
        String destination = "XXX";
        List<Route> result = RouteService.searchRoutesByOriginAndDestination(origin, destination);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }


}
