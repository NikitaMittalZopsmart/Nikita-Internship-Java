package com.zs.HobbiesProject;

import com.zs.HobbiesProject.Model.Badminton;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class to test Badminton Object.
 */
public class BadmintonTestCases {
    private final Badminton badmintonObject =new Badminton();
    Date date;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    /**
     * Initializing the Badminton Object.
     * @throws ParseException Throw Exception.
     */
    @Before
    public void start() throws ParseException {
        String inputDate ="20:01:12";
        date = timeFormat.parse(inputDate);
        badmintonObject.setStartTime(date);
        inputDate ="20:10:12";
        date = timeFormat.parse(inputDate);
        badmintonObject.setEndTime(date);
        inputDate ="2021-01-12";
        date = dateFormat.parse(inputDate);
        badmintonObject.setTickDate(date);
        badmintonObject.setNumberOfMove(2);
        badmintonObject.setResult("Won");
    }

    /**
     * Testing getResult function of Badminton class/
     */
    @Test
    public void testGetResult()
    {
        assertNotNull(badmintonObject.getResult());
    }
    /**
     * Testing getNumberOfMove function of Badminton class/
     */
    @Test
    public void testGetNumberOfMove()
    {
        assertEquals(3, badmintonObject.getNumberOfMove());
    }
    /**
     * Testing getStartTime function of Badminton class/
     */
    @Test
    public void testGetStartTime()
    {
        assertNotNull(badmintonObject.getStartTime());
    }
    @Test
    public void testGetEndTime()
    {
        assertNotNull(badmintonObject.getEndTime());
    }
    @Test
    public void testGetTickDate()
    {
        assertNotNull(badmintonObject.getTickDate());
    }

    /**
     * Testing that complete object is null or not.
     */
    @Test
    public void testCompleteObject()
    {
        assertNotNull(badmintonObject);
    }
}
