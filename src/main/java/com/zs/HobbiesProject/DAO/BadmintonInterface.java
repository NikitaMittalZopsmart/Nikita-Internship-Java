package com.zs.HobbiesProject.DAO;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import com.zs.HobbiesProject.Model.Badminton;
import com.zs.HobbiesProject.MainStart.LRUMain;

/**
 * This is the interface for finding streak of a user having badminton hobby.
 */
public interface BadmintonInterface {
    void create(Badminton badmintonObject, Logger logger) throws SQLException;
    void latestStreak(ArrayList<Date> dateList, Logger logger, String uidInput, LRUMain lruObject) throws SQLException;
    void streak(String uidInput, Logger logger, int choice, LRUMain lruObj) throws SQLException;

}
