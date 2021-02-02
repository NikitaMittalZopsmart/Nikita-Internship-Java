package com.zs.HobbiesProject.util;

import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class testConnection {
    ConnectionDb connectionObject;
    @Before
    public void setUp()
    {
        connectionObject=new ConnectionDb();
    }
    @Test
    public void testConnection()
    {

       Assert.assertTrue(connectionObject.connection()==null);

    }



}
