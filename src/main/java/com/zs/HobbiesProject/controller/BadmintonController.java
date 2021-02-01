package com.zs.HobbiesProject.controller;


import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.model.Badminton;
import com.zs.HobbiesProject.service.BadmintonService;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.Validator.Validator;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;


public class BadmintonController {

    public static void badmintonData(Logger logger, LRUCacheService lruObj) throws InvalidInputExceptions, ApplicationRunTimeExceptions {
        int choice, numberOfMove;
        String inputDate, startTime, endTime;
        Validator validatorObject = new Validator();
        BadmintonService badmintonServiceObject = new BadmintonService();
        DateFormat timeFormat = new SimpleDateFormat("yyyy:mm:dd:HH:mm:ss");
        Date date, startDate, endDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.Exit");
            choice = scan.nextInt();


            switch (choice) {
                case 1:
                    logger.info("Enter StartTime  EndTime  TickDate numberOfMove result userID");
                    Badminton badmintonObject = new Badminton();

                    startTime = scan.next();
                    endTime = scan.next();
                    try {
                        startDate = timeFormat.parse(startTime);
                        endDate = timeFormat.parse(endTime);
                    } catch (ParseException e) {
                        throw new InvalidInputExceptions(400, "StartTime must be less than end time.");
                    }


                    badmintonObject.setStartTime(startDate);
                    badmintonObject.setEndTime(endDate);
                    inputDate = scan.next();
                    try {
                        date = dateFormat.parse(inputDate);
                    } catch (ParseException e) {
                        throw new InvalidInputExceptions(400, "Date must be in yyyy-mm-dd format.");
                    }
                    badmintonObject.setTickDate(date);
                    numberOfMove = scan.nextInt();
                    validatorObject.validateStartEndTime(startDate, endDate);
                    validatorObject.validateNumberOfMove(numberOfMove);


                    badmintonObject.setNumberOfMove(numberOfMove);
                    badmintonObject.setResult(scan.next());
                    badmintonObject.setUserId(scan.next());
                    try {
                        badmintonServiceObject.create(badmintonObject, logger, lruObj);
                    } catch (SQLException e) {
                        throw new ApplicationRunTimeExceptions(500, "Bad request");
                    }
                    break;

                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    try {
                        badmintonServiceObject.latsestStreak(uidInput, logger, lruObj);
                    } catch (SQLException e) {
                        throw new ApplicationRunTimeExceptions(500, "Bad Request");
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
