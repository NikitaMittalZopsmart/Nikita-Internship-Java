package com.zs.HobbiesProject.exceptions;

import com.zs.HobbiesProject.util.LogImplement;

public class ApplicationRunTimeExceptions extends Exception{
    int errorCode;
    String errorDescription;
    public ApplicationRunTimeExceptions(int errorCode, String errorDescription)
    {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        LogImplement.getLog().warning(String.valueOf(errorCode)+errorDescription);
    }
}
