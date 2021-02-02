package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testStartPointException {
    @Test
    public void testStartPointException()throws StartPointException
    {
        new StartPointException("Start point point must be less than 8");
    }
}
