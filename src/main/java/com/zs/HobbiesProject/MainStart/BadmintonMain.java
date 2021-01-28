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
        int ch;
        String inputDate;
        BadmintonImp to = new BadmintonImp();
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date d;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scan = new Scanner(System.in);
        do {
            logger.info("1.Create 2.LatestStreak 3.Exit");
            ch = scan.nextInt();


            switch (ch) {
                case 1:
                    logger.info("Enter EndTime StartTime TickDate numberOfMove result");
                    Badminton bObj=new Badminton();

                    inputDate = scan.next();
                    d = timeFormat.parse(inputDate);
                    bObj.setStartTime(d);
                    inputDate = scan.next();
                    d = timeFormat.parse(inputDate);
                    bObj.setEndTime(d);
                    inputDate = scan.next();
                    d = dateFormat.parse(inputDate);
                    bObj.setTickDate(d);
                    bObj.setNumberOfMove(scan.nextInt());
                    bObj.setResult(scan.next());
                    to.create(bObj, logger);
                    break;

                case 2:
                    logger.info("please enter user id");
                    String uidInput = scan.next();
                    to.streak(uidInput, logger, ch,lruObj);
                    break;
                case 3:
                    System.exit(0);
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);

            }


        } while (ch < 5);
    }
}
