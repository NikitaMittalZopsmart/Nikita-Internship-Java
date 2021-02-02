package com.zs.HobbiesProject.dao;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.model.Badminton;
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
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;

public class TestDaoBadminton {
    BadmintonImp badmintonImpObject;
    DateFormat dateFormat;
    String inputDate;
    Date date1;
    ArrayList<Date> dateList;
    java.sql.Date date2;
    Badminton badmintonObject;
    PreparedStatement preStatement;
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    Connection connection;
    ConnectionDb connectioObject= Mockito.mock(ConnectionDb.class);
    PreparedStatement preparedStatement=Mockito.mock(PreparedStatement.class);
    @Before
    public void setUp() throws InvalidInputExceptions {
        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        badmintonImpObject = new BadmintonImp();
        dateList=new ArrayList<>();
        try {
            inputDate = "2021-01-12";
            date1 = dateFormat.parse(inputDate);
            date2= BadmintonImp.convertUtilToSql(date1);
            dateList.add(date2);
            inputDate = "2021-01-13";
            date1 = dateFormat.parse(inputDate);
            date2= BadmintonImp.convertUtilToSql(date1);
            dateList.add(date2);
            inputDate = "2021-01-14";
            date1 = dateFormat.parse(inputDate);
            date2= BadmintonImp.convertUtilToSql(date1);
            dateList.add(date2);

        } catch (ParseException p) {
            throw new InvalidInputExceptions(400, "Date must be in proper format");
        }
        String inputDate ="20:01:12";
        try {
            badmintonObject=new Badminton();
            date1 = timeFormat.parse(inputDate);
            badmintonObject.setStartTime(date1);
            inputDate = "20:10:12";
            date1 = timeFormat.parse(inputDate);
            badmintonObject.setEndTime(date1);
            inputDate = "2021-01-12";
            date1 = dateFormat.parse(inputDate);
            badmintonObject.setTickDate(date1);
            badmintonObject.setNumberOfMove(2);
            badmintonObject.setResult("Won");
            badmintonObject.setUserId("User4");
        }catch (ParseException p)
        {
            throw new InvalidInputExceptions(400,"Invalid inputs");
        }

    }

    @Test
    public void testLatestStreak() throws ApplicationRunTimeExceptions {
        try {
            Assert.assertEquals(2,badmintonImpObject.latestStreak(dateList));
      } catch (SQLException e) {
            throw new ApplicationRunTimeExceptions(500, "Bad request");
        }
    }
   @Test
    public void testConvertFromSqlToJavaDate() throws InvalidInputExceptions {
       String str="2015-03-31";

       java.sql.Date sqlDate=java.sql.Date.valueOf(str);
       try {
           java.util.Date utilDate = dateFormat.parse(str);
           Assert.assertEquals(utilDate, BadmintonImp.convertFromSQLDateToJAVADate(sqlDate));
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
           Assert.assertEquals(sqlDate,BadmintonImp.convertUtilToSql(utilDate));
       }catch(ParseException p)
       {
           throw new InvalidInputExceptions(400,"Invalid Input");
       }
   }
   @Test
    public void testCreate() throws SQLException {


       Mockito.when(connectioObject.connection()).thenReturn(connection);
       Mockito.when(connection.prepareStatement(any())).thenReturn(preStatement);
       preStatement.setDate(1, badmintonImpObject.convertUtilToSql(badmintonObject.getEndTime()));
       preStatement.setDate(2, badmintonImpObject.convertUtilToSql(badmintonObject.getStartTime()));
       preStatement.setDate(3, badmintonImpObject.convertUtilToSql(badmintonObject.getTickDate()));
       preStatement.setInt(4, badmintonObject.getNumberOfMove());
       preStatement.setString(5, badmintonObject.getResult());
       preStatement.setString(6, badmintonObject.getUserId());
       Mockito.when(preStatement.executeUpdate()).thenReturn(1);
       Assert.assertEquals(1,badmintonImpObject.create(badmintonObject, LogImplement.getLog()));
   }
   }


