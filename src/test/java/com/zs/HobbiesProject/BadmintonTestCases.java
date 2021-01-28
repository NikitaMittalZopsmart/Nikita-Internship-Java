package Testing;

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
    private Badminton badObject=new Badminton();
    Date d;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    /**
     * Initializing the Badminton Object.
     * @throws ParseException Throw Exception.
     */
    @Before
    public void start() throws ParseException {
        String inputDate ="20:01:12";
        d = timeFormat.parse(inputDate);
        badObject.setStartTime(d);
        inputDate ="20:10:12";
        d = timeFormat.parse(inputDate);
        badObject.setEndTime(d);
        inputDate ="2021-01-12";
        d = dateFormat.parse(inputDate);
        badObject.setTickDate(d);
        badObject.setNumberOfMove(2);
        badObject.setResult("Won");
    }

    /**
     * Testing getResult function of Badminton class/
     */
    @Test
    public void testGetResult()
    {
        assertNotNull(badObject.getResult());
    }
    /**
     * Testing getNumberOfMove function of Badminton class/
     */
    @Test
    public void testGetNuberOfMove()
    {
        assertEquals(3,badObject.getNumberOfMove());
    }
    /**
     * Testing getStartTime function of Badminton class/
     */
    @Test
    public void testGetStartTime()
    {
        assertNotNull(badObject.getStartTime());
    }
    @Test
    public void testGetEndTime()
    {
        assertNotNull(badObject.getEndTime());
    }
    @Test
    public void testGetTickDate()
    {
        assertNotNull(badObject.getTickDate());
    }

    /**
     * Testing that complete object is null or not.
     */
    @Test
    public void testCompleteObject()
    {
        assertNotNull(badObject);
    }
}
