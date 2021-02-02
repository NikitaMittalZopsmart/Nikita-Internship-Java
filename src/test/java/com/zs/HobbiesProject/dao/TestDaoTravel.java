package com.zs.HobbiesProject.dao;

import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.model.Travel;
import com.zs.HobbiesProject.util.ConnectionDb;
import com.zs.HobbiesProject.util.LogImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;

public class TestDaoTravel {
    TravelImp travelImpObject;

    DateFormat dateFormat;
    String inputDate;
    Date date1;
    ArrayList<Date> dateList;
    java.sql.Date date2;
    Travel travelObject;
    Connection connection = Mockito.mock(Connection.class);
    PreparedStatement preStatement;
    ConnectionDb connectioObject= Mockito.mock(ConnectionDb.class);
    PreparedStatement preparedStatement=Mockito.mock(PreparedStatement.class);
    @Before
    public void setUp() throws InvalidInputExceptions {
        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        travelImpObject = new TravelImp();
        Mockito.when(connectioObject.connection()).thenReturn(connection);
        try {
            Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);

        } catch( SQLException e) {
            e.getMessage();
        }
        dateList=new ArrayList<>();
        try {
            inputDate = "2021-01-12";
            date1 = dateFormat.parse(inputDate);
            date2= TravelImp.convertUtilToSql(date1);
            dateList.add(date2);
            inputDate = "2021-01-13";
            date1 = dateFormat.parse(inputDate);
            date2= TravelImp.convertUtilToSql(date1);
            dateList.add(date2);
            inputDate = "2021-01-14";
            date1 = dateFormat.parse(inputDate);
            date2= TravelImp.convertUtilToSql(date1);
            dateList.add(date2);

        } catch (ParseException p) {
            throw new InvalidInputExceptions(400, "Date must be in proper format");
        }
        try {
            travelObject=new Travel();
            String inputDate = "20:01:12";
            date1 = timeFormat.parse(inputDate);
            travelObject.setStartTime(date1);
            inputDate = "20:10:12";
            date1 = timeFormat.parse(inputDate);
            travelObject.setEndTime(date1);
            inputDate = "2021-01-12";
            date1 = dateFormat.parse(inputDate);
            travelObject.setTickDate(date1);
            travelObject.setDistance(22);
            travelObject.setEndPoint("UP");
            travelObject.setStartingPoint("MP");
            travelObject.setUserId("User2");
        }catch (ParseException p)
        {
            throw new InvalidInputExceptions(400,"Invalid inputs");
        }

    }

    @Test
    public void testLatestStreak() throws ApplicationRunTimeExceptions {
        try {
            Assert.assertEquals(2,travelImpObject.latestStreak(dateList));
        } catch (SQLException e) {
            throw new ApplicationRunTimeExceptions(500, "Bad request");
        }
    }
    @Test
    public void testConvertFromSqlToJavaDate() throws InvalidInputExceptions {
        String str="2015-01-31";

        java.sql.Date sqlDate=java.sql.Date.valueOf(str);
        try {
            java.util.Date utilDate = dateFormat.parse(str);

            Assert.assertEquals(utilDate, TravelImp.convertFromSQLDateToJAVADate(sqlDate));
        }catch(ParseException p)
        {
            throw  new InvalidInputExceptions(400,"Invalid input");
        }
    }
    @Test
    public void testConvertFromUtilToSql() throws InvalidInputExceptions {
        String str="2015-01-31";
        try {

            Date utilDate=new SimpleDateFormat("yyyy-mm-dd").parse(str);
            java.sql.Date sqlDate=java.sql.Date.valueOf(str);
            Assert.assertEquals(sqlDate,TravelImp.convertUtilToSql(utilDate));
        }catch(ParseException p)
        {
            throw new InvalidInputExceptions(400,"Invalid Input");
        }
    }
    @Test
    public void testCreate() throws SQLException {



//        preparedStatement.setDate(1, TravelImp.convertUtilToSql(travelObject.getEndTime()));
//        preparedStatement.setDate(2, TravelImp.convertUtilToSql(travelObject.getStartTime()));
//        preparedStatement.setDate(3, TravelImp.convertUtilToSql(travelObject.getTickDate()));
//        preparedStatement.setString(4, travelObject.getStartingPoint());
//        preparedStatement.setString(5, travelObject.getEndPoint());
//        preparedStatement.setInt(6, (int) travelObject.getDistance());
//        preparedStatement.setString(7, travelObject.getUserId());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Assert.assertEquals(1,travelImpObject.create(travelObject, LogImplement.getLog()));
    }
}
