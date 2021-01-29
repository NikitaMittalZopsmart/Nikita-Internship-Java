package com.zs.HobbiesProject.MainStart;


import com.zs.HobbiesProject.DAO.*;
import com.zs.HobbiesProject.Model.Travel;

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
public class TravelMain {


    private TravelMain() {
    }

    /**
     * This method is to select 1 option among create,LatestStreak,LongestStreak for Travel hobby.
     *
     * @param logger A Logger Object
     * @throws SQLException   Throwing SQLExceptions.
     * @throws ParseException Throwing ParseExceptions.
     */
    public static void travelData(Logger logger, LRUMain lruObject) throws SQLException, ParseException {
        int choice;
        String inputDate;
        String startPoint=null;
        String endPoint=null;
        TravelImp travelImpObject = new TravelImp();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date;
        int startPointFlag = 1;
        int endPointFlag=1;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    logger.info("Enter Distance StartTime EndTime StartPoint EndPoint TickDate");
                    Travel travelObject = new Travel();
                    travelObject.setDistance(scan.nextFloat());
                    inputDate = scan.next();
                    date = timeFormat.parse(inputDate);
                    travelObject.setStartTime(date);
                    inputDate = scan.next();
                    date = timeFormat.parse(inputDate);
                    travelObject.setEndTime(date);

                    while (startPointFlag == 1) {

                        startPoint = scan.next();
                        startPointFlag = 0;
                        try {
                            if (startPoint.length() > 8)
                                throw new CustomException();
                        } catch (CustomException e) {
                            e.startPointException(logger);
                            startPointFlag = 1;
                        }
                    }

                    while (endPointFlag== 1) {
                        endPoint = scan.next();
                        endPointFlag = 0;
                        try {
                            if (endPoint.length() > 8)
                                throw new CustomException();
                        } catch (CustomException e) {
                            e.endPointException(logger);
                            endPointFlag = 1;
                        }
                    }
                    travelObject.setStartingPoint(startPoint);
                    travelObject.setEndPoint(endPoint);
                    inputDate = scan.next();
                    date = dateFormat.parse(inputDate);
                    travelObject.setTickDate(date);
                    travelImpObject.create(travelObject, logger);
                    break;
                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    travelImpObject.streak(uidInput, logger, choice, lruObject);
                    break;
                case 3:
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);

            }

        }
        while (choice < 5);
    }
}


