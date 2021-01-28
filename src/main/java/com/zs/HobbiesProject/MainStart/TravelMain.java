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
    public static void travelData(Logger logger,LRUMain lruObj) throws SQLException, ParseException {
        int ch;
        String inputDate;
        TravelImp to = new TravelImp();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date d;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.LongestStreak 4.Exit");
            ch = scan.nextInt();


            switch (ch) {
                case 1:
                    logger.info("Enter Distance StartTime EndTime StartPoint EndPoint TickDate");
                    Travel tObj = new Travel();
                    tObj.setDistance(scan.nextFloat());
                    inputDate = scan.next();
                    d = timeFormat.parse(inputDate);
                    tObj.setStartTime(d);
                    inputDate = scan.next();
                    d = timeFormat.parse(inputDate);
                    tObj.setEndTime(d);
                    String startPoint = scan.next();
                    String endPoint = scan.next();
                    try {
                        if (startPoint.length() > 30)
                            throw new CustomException();


                    } catch (CustomException e) {

                        e.startPointException(logger);
                    }
                    try {
                        if (endPoint.length() > 30)
                            throw new CustomException();
                    } catch (CustomException e) {
                        e.endPointException(logger);
                    }
                    tObj.setStartingPoint(startPoint);
                    tObj.setEndPoint(endPoint);
                    inputDate = scan.next();
                    d = dateFormat.parse(inputDate);
                    tObj.setTickDate(d);
                    to.create(tObj, logger);
                    break;

                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    to.streak(uidInput, logger, ch,lruObj);
                    break;
                case 4:
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);


            }


        } while (ch < 5);
    }
}


