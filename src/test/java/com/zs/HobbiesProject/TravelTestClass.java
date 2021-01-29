package com.zs.HobbiesProject;

import com.zs.HobbiesProject.Model.Travel;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class to test travel object.
 */
public class TravelTestClass {
    private final Travel travelObject =new Travel();
    Date d;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    /**
     * Initializing all value for Travel Object.
     * @throws ParseException Throwing exception.
     */
    @Before
    public void start() throws ParseException {
        String inputDate ="20:01:12";
        d = timeFormat.parse(inputDate);
        travelObject.setStartTime(d);
        inputDate ="20:10:12";
        d = timeFormat.parse(inputDate);
        travelObject.setEndTime(d);
        inputDate ="2021-01-12";
        d = dateFormat.parse(inputDate);
        travelObject.setTickDate(d);
        travelObject.setDistance(22);
        travelObject.setEndPoint("UP");
        travelObject.setStartingPoint("MP");

    }
    @Test
    public void testGetStartPoint()
    {
        assertNotNull(travelObject.getStartingPoint());
    }

    /**
     * Testing Complete Object.
     */
    @Test
    public void testCompleteTravelObject()
    {
        assertNotNull(travelObject);
    }
    @Test
    public void testGetEEndPoint()
    {
        assertNotNull(travelObject.getEndPoint());
    }
    @Test
    public void testGetDistance()
    {
        assertEquals(3,(int) travelObject.getDistance());
    }
    @Test
    public void testGetStartTime()
    {
        assertNotNull(travelObject.getStartTime());
    }
    @Test
    public void testGetEndTime()
    {
        assertNotNull(travelObject.getEndTime());
    }
    @Test
    public void testGetTickDate()
    {
        assertNotNull(travelObject.getTickDate());
    }

}
