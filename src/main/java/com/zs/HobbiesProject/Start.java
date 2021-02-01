package com.zs.HobbiesProject;


import com.zs.HobbiesProject.controller.BadmintonController;
import com.zs.HobbiesProject.controller.TravelController;
import com.zs.HobbiesProject.cache.LRUCacheService;
import com.zs.HobbiesProject.exceptions.ApplicationRunTimeExceptions;
import com.zs.HobbiesProject.exceptions.InvalidInputExceptions;
import com.zs.HobbiesProject.util.LogImplement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is the starting class of this hobby tracker system.
 */
public class Start{

    static Scanner scan = new Scanner(System.in);

    public static void main(String args[]) {
        int choice;
        LRUCacheService lruObject =new LRUCacheService();


        while (true) {
            LogImplement.getLog().info("1.Badminton 2.Travel 3.Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    try {
                        BadmintonController.badmintonData(LogImplement.getLog(), lruObject);
                    } catch (InvalidInputExceptions | ApplicationRunTimeExceptions e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    try {
                        TravelController.travelData(LogImplement.getLog(), lruObject);
                    } catch (InvalidInputExceptions | ApplicationRunTimeExceptions e) {
                        e.printStackTrace();
                    }

                    break;
                case 3:
                    System.exit(0);
                default:
                    LogImplement.getLog().info("Please enter your correct choice");


            }
        }
    }
}
