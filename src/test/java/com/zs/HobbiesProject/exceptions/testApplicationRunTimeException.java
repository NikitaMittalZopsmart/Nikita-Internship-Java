package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testApplicationRunTimeException {
    @Test
    public void testRunTimeException()throws ApplicationRunTimeExceptions
    {
        new ApplicationRunTimeExceptions(500,"Bad Request");
    }
}
