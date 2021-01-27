package main.java.com.zs.HobbiesProject.DAO;


import main.java.com.zs.HobbiesProject.MainStart.LRUMain;
import main.java.com.zs.HobbiesProject.Model.Travel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This is an interface for travel hobby.
 */
public interface TravelInterface {
    void create(Travel tObj, Logger logger) throws SQLException;
    void latestStreak(ArrayList<Date> arr, Logger logger, String uidInput, LRUMain lruObj) throws SQLException;
    void streak(String uidInput, Logger logger, int ch, LRUMain lruObj) throws SQLException;

}
