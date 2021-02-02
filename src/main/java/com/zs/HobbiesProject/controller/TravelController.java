package com.zs.HobbiesProject.controller;



import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.model.Travel;
import com.zs.HobbiesProject.service.TravelService;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.Validator.Validator;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is to created to perform some operations like Create,LatestStreak,LongestStreak for travel hobby.
 */
public class TravelController {


    private TravelController() {
    }

    /**
     * This method is to select 1 option among create,LatestStreak,LongestStreak for Travel hobby.
     *
     * @param logger A Logger Object
     * @throws SQLException   Throwing SQLExceptions.
     * @throws ParseException Throwing ParseExceptions.
     */
    public static void travelData(Logger logger, LRUCacheService lruObj) throws InvalidInputExceptions, ApplicationRunTimeExceptions {
        int choice;
        String inputDate,startTime,endTime;
        TravelService travelService=new TravelService();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date,startTimeDate,endTimeDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Validator validator=new Validator();
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.Exit");
            choice = scan.nextInt();


            switch (choice) {
                case 1:
                    logger.info("Enter Distance StartTime EndTime StartPoint EndPoint TickDate UserID");
                    Travel travelObject = new Travel();
                    travelObject.setDistance(scan.nextFloat());

                    try {
                        startTime = scan.next();
                        startTimeDate = timeFormat.parse(startTime);
                        endTime = scan.next();
                        endTimeDate = timeFormat.parse(endTime);
                    }catch(ParseException e)
                    {
                        throw  new InvalidInputExceptions(400,"Start time must be less than end time.");
                    }


                    validator.validateStartEndTime(startTimeDate,endTimeDate);
                    travelObject.setStartTime(startTimeDate);
                    travelObject.setEndTime(endTimeDate);
                    String startPoint = scan.next();
                    String endPoint = scan.next();
                    validator.validateStartPoint(startPoint);
                    validator.validateEndPoint(endPoint);
                    travelObject.setStartingPoint(startPoint);
                    travelObject.setEndPoint(endPoint);
                    inputDate = scan.next();
                    try {
                        date = dateFormat.parse(inputDate);
                    }catch(ParseException e)
                    {
                        throw new InvalidInputExceptions(400,"Date must be in yyyy-mm-dd format.");
                    }
                    travelObject.setTickDate(date);
                    travelObject.setUserId(scan.next());
                    try {
                        travelService.create(travelObject, logger, lruObj);
                    }catch(SQLException e)
                    {
                        throw new ApplicationRunTimeExceptions(500,"Bad request");
                    }
                    break;

                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    try {
                        travelService.latsestStreak(uidInput, logger, lruObj);
                    }catch(SQLException e)
                    {
                        throw  new ApplicationRunTimeExceptions(500,"Bad request");
                    }
                    break;
                case 3:
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);


            }


        } while (choice < 5);
    }
}


