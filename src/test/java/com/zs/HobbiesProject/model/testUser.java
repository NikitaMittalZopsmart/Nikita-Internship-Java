package com.zs.HobbiesProject.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class testUser {
    User u;
    @Before
    public void set()
    {

       u=new User();
       u.setHobbyName("travel");
       u.setuID("User2");
    }
    @Test
    public void testGetUserId()
    {
        Assert.assertEquals("User2",u.getuID());
    }
    @Test
    public void testGetHobbyName()
    {
        Assert.assertEquals("travel",u.getHobbyName());
    }
}
