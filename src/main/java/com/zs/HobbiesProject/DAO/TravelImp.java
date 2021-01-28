package com.zs.HobbiesProject.DAO;


import com.zs.HobbiesProject.MainStart.LRUMain;
import com.zs.HobbiesProject.Model.Travel;
import com.zs.HobbiesProject.Util.ConnectionDb;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;
import com.zs.HobbiesProject.MainStart.LRUMain;
import com.zs.HobbiesProject.Model.Travel;
/**
 * This class is implementing Travel Interface to perform database operations.
 */
public class TravelImp implements TravelInterface {

    private PreparedStatement stmt;
    private PreparedStatement stmt2;
    private Connection c;
    private ConnectionDb cObj=new ConnectionDb();
    String uid;
    ResultSet rs;

    Scanner scan = new Scanner(System.in);

    /**
     * This funtion is converting a date from java.util to java.sql.
     * @param uDate A date in java.util format.
     * @return A date in java.sql format.
     */
    private static Date convertUtilToSql(java.util.Date uDate) {
        return (new Date(uDate.getTime()));
    }

    /**
     * This funtion is converting a date from java.sql to java.util .
     * @param sqlDate A date in java.sql format.
     * @return A date in java.util format.
     */
    public static java.util.Date convertFromSQLDateToJAVADate(
            Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    /**
     * This function is used to create a preparedStatement for inserting values in travel table.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements1() throws SQLException {
        c = cObj.connection();
        stmt = c.prepareStatement("insert into travel values(?,?,?,?,?,?,?);");
    }

    /**
     * This function is used to create a preparedStatement to fetch the travel table dat for a particular user. in travel table.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements2() throws SQLException {
        c = cObj.connection();
        stmt2 = c.prepareStatement("select * from travel where user_id=? order by hobby_date ;");
    }

    /**
     * This method is created to insert a record in travel table.
     * @param tObj An object of travel class.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void create(Travel tObj, Logger logger) throws SQLException {

        logger.info("enter user id");
        prepareStatements1();
        uid = scan.next();
        stmt.setDate(1, convertUtilToSql(tObj.getEndTime()));
        stmt.setDate(2, convertUtilToSql(tObj.getStartTime()));
        stmt.setDate(3, convertUtilToSql(tObj.getTickDate()));
        stmt.setString(4, tObj.getStartingPoint());
        stmt.setString(5, tObj.getEndPoint());
        stmt.setInt(6, (int) tObj.getDistance());
        stmt.setString(7, uid);
        int m = stmt.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
    }

    /**
     * This method is created to calculate the latest streak for travel hobby for a particular user.
     * @param arr An arraylist have dates in a particular order.
     * @param logger A logger Object.
     * @param uidInput User Id for which we are finding lateststreak.
     * @throws SQLException Throwing SQLException.
     */
    @Override
    public void latestStreak(ArrayList<java.util.Date> arr, Logger logger, String uidInput, LRUMain lruObj) throws SQLException {
        logger.info("In lateststreak");
        int stIndex = 0;
        int endIndex;
        int max = 0;
        for (int j = 0; j < arr.size() - 1; j++) {
            long noOfDaysBetween = (long) arr.get(j + 1).getDate() - arr.get(j).getDate();
            if (noOfDaysBetween == 1) {
                endIndex = j + 1;
                max = endIndex - stIndex;

            } else {
                stIndex = j + 1;
            }
        }
        logger.info("latestStreak"+max);
        lruObj.putInCache(uidInput,logger,"travel",max);

    }

    /**
     * This method is to get the dates for travel hobby for a particular object.
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger A logger Object.
     * @param ch A integer to decide which method is to call among lateststreak and loneststreak.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void streak(String uidInput, Logger logger, int ch,LRUMain lruObj) throws SQLException {

       if(lruObj.getValue(uidInput,logger,"travel"))
           return ;
        logger.info("In streak");
        List<java.util.Date> arr = new ArrayList<>();
        prepareStatements2();
        stmt2.setString(1, uidInput);
        rs = stmt2.executeQuery();
        TreeMap<Date, ArrayList<String>> valueMap = new TreeMap<>();
        valueMap.clear();
        while (rs.next()) {
            Date d = rs.getDate(3);
            String startTime = rs.getString(1);
            String endTime = rs.getString(1);
            java.util.Date d1 = convertFromSQLDateToJAVADate(d);
            valueMap.putIfAbsent((Date) d1, new ArrayList<>());
            valueMap.get(d1).add(startTime);
            valueMap.get(d1).add(endTime);
            valueMap.get(d1).add("travel");
        }
        Set<Date> s;
        s = valueMap.keySet();
        arr.addAll(s);
        logger.info("array" + arr);
        if (ch == 2)
            latestStreak((ArrayList<java.util.Date>) arr, logger,uidInput,lruObj);


    }
}



