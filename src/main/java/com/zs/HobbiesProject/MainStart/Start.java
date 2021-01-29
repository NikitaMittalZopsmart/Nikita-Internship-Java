package com.zs.HobbiesProject.MainStart;


import com.zs.HobbiesProject.Util.LogImplement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is the starting class of this hobby tracker system.
 */
public class Start{

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ParseException {
        int choice;
        LRUMain lruObject =new LRUMain();
       Logger logger= LogImplement.getLog();

        while (true) {
            logger.info("1.Badminton 2.Travel 3.Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:BadmintonMain.badmintonData(logger, lruObject);
                       break;
                case 2:
                    TravelMain.travelData(logger, lruObject);
                    break;
                case 3:
                    System.exit(0);
                default:
                    LogImplement.getLog().info("Please enter your correct choice");

            }
        }
    }
}
