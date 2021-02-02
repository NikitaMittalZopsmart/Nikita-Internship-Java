package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testInvalidInputException {
    @Test
    public void testInvalidInputException()throws InvalidInputExceptions
    {
        new InvalidInputExceptions(400,"Invalid Inputs");
    }
}
