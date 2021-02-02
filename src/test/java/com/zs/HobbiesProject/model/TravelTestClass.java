package com.zs.HobbiesProject.model;

import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.model.Travel;
import com.zs.HobbiesProject.exceptions.EndPointException;
import com.zs.HobbiesProject.exceptions.StartEndTimeException;
import com.zs.HobbiesProject.exceptions.StartPointException;
import com.zs.HobbiesProject.Validator.Validator;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test travel object.
 */
public class TravelTestClass {
    private Travel traObject=new Travel();
    Date d;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    Validator validator=new Validator();
    /**
     * Intializing all value for Travel Object.
     * @throws ParseException Throwing exception.
     */
    @Before
    public void start() throws ParseException {
        String inputDate ="20:01:12";
        d = timeFormat.parse(inputDate);
        traObject.setStartTime(d);
        inputDate ="20:10:12";
        d = timeFormat.parse(inputDate);
        traObject.setEndTime(d);
        inputDate ="2021-01-12";
        d = dateFormat.parse(inputDate);
        traObject.setTickDate(d);
        traObject.setDistance(22);
        traObject.setEndPoint("UP");
        traObject.setStartingPoint("MP");
        traObject.setUserId("User2");
    }



    @Test
    public void testGetStartPoint()
    {
        assertNotNull(traObject.getStartingPoint());
    }

    /**
     * Testing Complete Object.
     */
    @Test
    public void testCompleteTravelObject()
    {
        assertNotNull(traObject);
    }
    @Test
    public void testGetEEndPoint()
    {
        assertNotNull(traObject.getEndPoint());
    }
    @Test
    public void testGetDistance()
    {
        assertEquals(22,(int) traObject.getDistance());
    }
    @Test
    public void testGetStartTime()
    {
        assertNotNull(traObject.getStartTime());
    }
    @Test
    public void testGetEndTime()
    {
        assertNotNull(traObject.getEndTime());
    }
    @Test
    public void testGetTickDate()
    {
        assertNotNull(traObject.getTickDate());
    }

}
