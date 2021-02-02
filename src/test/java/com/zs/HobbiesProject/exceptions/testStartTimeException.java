package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testStartTimeException {
    @Test
    public void testStartTimeException()throws StartEndTimeException
    {
        new StartEndTimeException("start time must be less than end time");
    }
}
