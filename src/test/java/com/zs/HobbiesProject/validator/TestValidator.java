package com.zs.HobbiesProject.validator;

import com.zs.HobbiesProject.Validator.Validator;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.service.BadmintonService;
import org.junit.Before;
import org.junit.Test;

public class TestValidator {
    Validator validator;
    @Before
    public  void setUp()
    {

        validator=new Validator();
    }
    @Test(expected = InvalidInputExceptions.class)
    public void testValidStartPoint() throws InvalidInputExceptions {
        validator.validateEndPoint("MathuraVrindawan");
    }
    @Test(expected = InvalidInputExceptions.class)
    public void testEndPoint() throws InvalidInputExceptions {
        validator.validateEndPoint("Bengaluru");
    }
    @Test(expected= InvalidInputExceptions.class)
    public void testvValidateNumberOfMove() throws InvalidInputExceptions {
        validator.validateNumberOfMove(12);
    }


}
