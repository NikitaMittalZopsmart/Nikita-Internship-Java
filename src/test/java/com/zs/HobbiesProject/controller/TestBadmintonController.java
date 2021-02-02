package com.zs.HobbiesProject.controller;

import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.model.Badminton;
import com.zs.HobbiesProject.service.BadmintonService;
import com.zs.HobbiesProject.util.LogImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBadmintonController {
    LRUCacheService lruServiceObject= Mockito.mock(LRUCacheService.class);
    BadmintonService badmintonServiceObject= Mockito.mock(BadmintonService.class);
    BadmintonController badmintonController;
    Badminton badminton;
    Date date1;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    @Before
    public void set() throws InvalidInputExceptions {
        badminton=new Badminton();
        badmintonController=new BadmintonController();
        String inputDate ="20:01:12";
        try {
            badminton = new Badminton();
            date1 = timeFormat.parse(inputDate);
            badminton.setStartTime(date1);
            inputDate = "20:10:12";
            date1 = timeFormat.parse(inputDate);
            badminton.setEndTime(date1);
            inputDate = "2021-01-12";
            date1 = dateFormat.parse(inputDate);
            badminton.setTickDate(date1);
            badminton.setNumberOfMove(2);
            badminton.setResult("Won");
            badminton.setUserId("User4");
        }catch (ParseException p)
        {
            throw new InvalidInputExceptions(400,"Invalid Inputs");
        }

    }

  /* @Test(expected = ApplicationRunTimeExceptions.class|InvalidInputExceptions.class)
    public void testBadmintonData() throws InvalidInputExceptions, ApplicationRunTimeExceptions {
        ((badmintonController.badmintonData(LogImplement.getLog(), lruServiceObject)));
    }*/
}
