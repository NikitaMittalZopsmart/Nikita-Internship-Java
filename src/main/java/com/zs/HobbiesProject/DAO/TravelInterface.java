package com.zs.HobbiesProject.DAO;


import com.zs.HobbiesProject.MainStart.LRUMain;
import com.zs.HobbiesProject.Model.Travel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This is an interface for travel hobby.
 */
public interface TravelInterface {
    void create(Travel travelObject, Logger logger) throws SQLException;
    void latestStreak(ArrayList<Date> dateList, Logger logger, String uidInput, LRUMain lruObject) throws SQLException;
    void streak(String uidInput, Logger logger, int choice, LRUMain lruObject) throws SQLException;

}
