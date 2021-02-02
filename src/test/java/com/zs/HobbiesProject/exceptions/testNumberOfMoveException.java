package com.zs.HobbiesProject.exceptions;

import org.junit.Test;

public class testNumberOfMoveException {
    @Test
    public void testNumberOfMoveException()throws NumberOfMoveException
    {
        new NumberOfMoveException("Number of move must be less than 10");
    }
}
