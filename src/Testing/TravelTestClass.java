package Testing;

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
    private Travel traObject=new Travel();
    Date d;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

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
        assertEquals(3,(int) traObject.getDistance());
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
