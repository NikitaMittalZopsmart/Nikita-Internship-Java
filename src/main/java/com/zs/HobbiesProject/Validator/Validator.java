package com.zs.HobbiesProject.Validator;

import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;

import java.util.Date;

public class Validator {
    public void validateStartPoint(String startPoint) throws InvalidInputExceptions {
        if(startPoint.length() >8) {
            throw new InvalidInputExceptions(400,"Start point must be less than 9");

        }

    }
    public void validateEndPoint(String endPoint) throws InvalidInputExceptions{
        if(endPoint.length()>8)
            throw new InvalidInputExceptions(400,"End Point must be less than 9");

    }
    public void validateNumberOfMove(int numberOfMove) throws InvalidInputExceptions
    {
        if(numberOfMove>10)
            throw new InvalidInputExceptions(400,"Number of move must be less than 11");

    }
    public void validateStartEndTime(Date startTime, Date endTime) throws InvalidInputExceptions
    {
     if(startTime.getTime()>endTime.getTime())
         throw new InvalidInputExceptions(400,"Start time must be less than end time");


    }
}
