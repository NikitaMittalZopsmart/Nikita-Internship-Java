package com.zs.HobbiesProject.exceptions;

import com.zs.HobbiesProject.util.LogImplement;

import java.text.ParseException;

public class InvalidInputExceptions extends Exception{
    int errorCode;
    String errorDescription;
    public InvalidInputExceptions(int errorCode, String errorDescription)
    {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        LogImplement.getLog().info(String.valueOf(errorCode)+errorDescription);
    }


}
