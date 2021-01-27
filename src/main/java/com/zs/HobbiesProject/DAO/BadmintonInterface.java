package main.java.com.zs.HobbiesProject.DAO;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import main.java.com.zs.HobbiesProject.Model.Badminton;
import main.java.com.zs.HobbiesProject.MainStart.LRUMain;
public interface BadmintonInterface {
    void create(Badminton tObj, Logger logger) throws SQLException;
    void latestStreak(ArrayList<Date> arr, Logger logger, String uidInput,LRUMain lruObj) throws SQLException;
    void streak(String uidInput, Logger logger, int ch, LRUMain lruObj) throws SQLException;

}
