package com.zs.HobbiesProject.dao;


import com.zs.HobbiesProject.model.Travel;
import com.zs.HobbiesProject.util.ConnectionDb;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;

/**
 * This class is implementing Travel Interface to perform database operations.
 */
public class TravelImp  {

    private PreparedStatement stmt;
    private PreparedStatement stmt2;
    private Connection connection;
    private ConnectionDb connectionObject = new ConnectionDb();
    String uid;
    ResultSet rs;

    Scanner scan = new Scanner(System.in);

    /**
     * This funtion is converting a date from java.util to java.sql.
     * @param uDate A date in java.util format.
     * @return A date in java.sql format.
     */
    public static Date convertUtilToSql(java.util.Date uDate) {
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
    public PreparedStatement prepareStatements1() throws SQLException {
        connection = connectionObject.connection();
        stmt = connection.prepareStatement("insert into travel values(?,?,?,?,?,?,?);");
        return stmt;
    }

    /**
     * This function is used to create a preparedStatement to fetch the travel table dat for a particular user. in travel table.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements2() throws SQLException {
        connection = connectionObject.connection();
        stmt2 = connection.prepareStatement("select * from travel where user_id=? order by hobby_date ;");
    }

    /**
     * This method is created to insert a record in travel table.
     * @param travelObject An object of travel class.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */

    public int create(Travel travelObject, Logger logger) throws SQLException {


        connection = connectionObject.connection();
        stmt = connection.prepareStatement("insert into travel values(?,?,?,?,?,?,?);");


        stmt.setDate(1, convertUtilToSql(travelObject.getEndTime()));
        stmt.setDate(2, convertUtilToSql(travelObject.getStartTime()));
        stmt.setDate(3, convertUtilToSql(travelObject.getTickDate()));
        stmt.setString(4, travelObject.getStartingPoint());
        stmt.setString(5, travelObject.getEndPoint());
        stmt.setInt(6, (int) travelObject.getDistance());
        stmt.setString(7, travelObject.getUserId());
        int m = stmt.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
        return m;
    }

    /**
     * This method is created to calculate the latest streak for travel hobby for a particular user.
     * @param arr An arraylist have dates in a particular order.


     * @throws SQLException Throwing SQLException.
     */

    public int latestStreak(ArrayList<java.util.Date> arr) throws SQLException {
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
        return max;

    }

    /**
     * This method is to get the dates for travel hobby for a particular object.
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */

    public int streak(String uidInput, Logger logger) throws SQLException {
        List<java.util.Date> arr = new ArrayList<>();
        prepareStatements2();
        stmt2.setString(1, uidInput);
        rs = stmt2.executeQuery();
        TreeMap<java.sql.Date, ArrayList<String>> valueMap = new TreeMap<>();
        valueMap.clear();
        while (rs.next()) {
            java.sql.Date d = rs.getDate(3);
            String startTime = rs.getString(2);
            String endTime = rs.getString(1);
            java.util.Date d1 = convertFromSQLDateToJAVADate(d);
            valueMap.putIfAbsent((java.sql.Date) d1, new ArrayList<>());
            valueMap.get(d1).add(startTime);
            valueMap.get(d1).add(endTime);
            valueMap.get(d1).add("badminton");
        }
        Set<java.sql.Date> s;
        s = valueMap.keySet();
        arr.addAll(s);
        logger.info("array" + arr);
        int max=latestStreak((ArrayList<java.util.Date>) arr);
        return max;

    }
}



