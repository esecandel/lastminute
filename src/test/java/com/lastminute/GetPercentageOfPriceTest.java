package com.lastminute;

import com.lastminute.service.FlightSearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@RunWith(MockitoJUnitRunner.class)
public class GetPercentageOfPriceTest {

    @Test
    public void getPercentageTest_After30days(){
        OffsetDateTime date = OffsetDateTime.now().plus(31, ChronoUnit.DAYS);
        Assert.assertEquals(0.8d, FlightSearchService.getPercentage(date),0d);
    }

    @Test
    public void getPercentageTest_Between30and16days(){
        OffsetDateTime date = OffsetDateTime.now().plus(30, ChronoUnit.DAYS).minus(1, ChronoUnit.HOURS);
        Assert.assertEquals(1d, FlightSearchService.getPercentage(date),0d);
        date = OffsetDateTime.now().plus(16, ChronoUnit.DAYS);
        Assert.assertEquals(1d, FlightSearchService.getPercentage(date),0d);
    }

    @Test
    public void getPercentageTest_Between15and3days(){
        OffsetDateTime date = OffsetDateTime.now().plus(16, ChronoUnit.DAYS).minus(1, ChronoUnit.HOURS);
        Assert.assertEquals(1.2d, FlightSearchService.getPercentage(date),0d);
        date = OffsetDateTime.now().plus(3, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS);
        Assert.assertEquals(1.2d, FlightSearchService.getPercentage(date),0d);
    }

    @Test
    public void getPercentageTest_After3days(){
        OffsetDateTime date = OffsetDateTime.now().plus(3, ChronoUnit.DAYS).minus(1, ChronoUnit.HOURS);
        Assert.assertEquals(1.5d, FlightSearchService.getPercentage(date),0d);
    }

}
