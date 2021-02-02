package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testEndPointException {
    @Test
    public void testEndPointException()throws EndPointException
    {
        new EndPointException("End point must be less than 8");
    }
}
