package com.zs.HobbiesProject.MainStart;


import com.zs.HobbiesProject.DAO.BadmintonImp;
import com.zs.HobbiesProject.Model.Badminton;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;


public class BadmintonMain {

    public static void badmintonData(Logger logger, LRUMain lruObj) throws SQLException, ParseException {
        int choice;
        String inputDate;
        BadmintonImp badmintonImpObject = new BadmintonImp();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.Exit");
            choice = scan.nextInt();


            switch (choice) {
                case 1:
                    logger.info("Enter EndTime StartTime TickDate numberOfMove result");
                    Badminton badmintonObj =new Badminton();
                    inputDate = scan.next();
                    date = timeFormat.parse(inputDate);
                    badmintonObj.setStartTime(date);
                    inputDate = scan.next();
                    date = timeFormat.parse(inputDate);
                    badmintonObj.setEndTime(date);
                    inputDate = scan.next();
                    date = dateFormat.parse(inputDate);
                    badmintonObj.setTickDate(date);
                    badmintonObj.setNumberOfMove(scan.nextInt());
                    badmintonObj.setResult(scan.next());
                    badmintonImpObject.create(badmintonObj, logger);
                    break;

                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    badmintonImpObject.streak(uidInput, logger, choice,lruObj);
                    break;
                case 3:
                    System.exit(0);
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);

            }


        } while (choice < 5);
    }
}
