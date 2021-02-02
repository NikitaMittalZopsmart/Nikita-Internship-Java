package com.zs.HobbiesProject.dao;


import com.zs.HobbiesProject.model.Badminton;
import com.zs.HobbiesProject.util.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class BadmintonImp {

    String uid;
    ResultSet resultSet;
    Scanner scan = new Scanner(System.in);
    private PreparedStatement insertStatement;
    private PreparedStatement fetchStatement;
    private Connection connection;
    private ConnectionDb connectionObject = new ConnectionDb();

    /**
     * This funtion is converting a date from java.util to java.sql.
     *
     * @param uDate A date in java.util format.
     * @return A date in java.sql format.
     */
    public static java.sql.Date convertUtilToSql(Date uDate) {
        return (new java.sql.Date(uDate.getTime()));
    }

    /**
     * This funtion is converting a date from java.sql to java.util .
     *
     * @param sqlDate A date in java.sql format.
     * @return A date in java.util format.
     */
    public static Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new java.sql.Date(sqlDate.getTime());
        }
        return javaDate;
    }

    /**
     * This function is used to create a preparedStatement for inserting values in travel table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public PreparedStatement prepareStatements1() throws SQLException {
        connection = connectionObject.connection();
        insertStatement = connection.prepareStatement("insert into badminton values(?,?,?,?,?,?);");
         return insertStatement;
    }

    /**
     * This function is used to create a preparedStatement to fetch the travel table dat for a particular user. in travel table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public PreparedStatement prepareStatements2() throws SQLException {
        connection = connectionObject.connection();
        fetchStatement = connection.prepareStatement("select * from badminton where user_id=? order by hobby_date ;");
        return fetchStatement;
    }

    /**
     * This method is created to insert a record in badminton table.
     *
     * @param badmintonObject An object of travel class.
     * @param logger          A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */

    public int create(Badminton badmintonObject, Logger logger) throws SQLException {

        insertStatement= prepareStatements1();
        insertStatement.setDate(1, convertUtilToSql(badmintonObject.getEndTime()));
        insertStatement.setDate(2, convertUtilToSql(badmintonObject.getStartTime()));
        insertStatement.setDate(3, convertUtilToSql(badmintonObject.getTickDate()));
        insertStatement.setInt(4, badmintonObject.getNumberOfMove());
        insertStatement.setString(5, badmintonObject.getResult());

        insertStatement.setString(6, badmintonObject.getUserId());
        int m = insertStatement.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
        return m;
    }

    /**
     * This method is created to calculate the latest streak for badminton hobby for a particular user.
     *
     * @param dateList An arraylist have dates in a particular order.

     * @throws SQLException Throwing SQLException.
     */

    public int latestStreak(ArrayList<Date> dateList) throws SQLException {
        int startIndex = 0;
        int endIndex;
        int max = 0;
        for (int j = 0; j < dateList.size() - 1; j++) {
            long noOfDaysBetween = (long) dateList.get(j + 1).getDate() - dateList.get(j).getDate();
            if (noOfDaysBetween == 1) {
                endIndex = j + 1;
                max = endIndex - startIndex;

            } else {
                startIndex = j + 1;
            }
        }
        return max;

    }

    /**
     * This method is to get the dates for badminton hobby for a particular object.
     *
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger   A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */

    public int streak(String uidInput, Logger logger) throws SQLException {
        List<Date> dateList = new ArrayList<>();
        fetchStatement=prepareStatements2();
        fetchStatement.setString(1, uidInput);
        resultSet = fetchStatement.executeQuery();
        TreeMap<java.sql.Date, ArrayList<String>> valueMap = new TreeMap<>();
        valueMap.clear();
        while (resultSet.next()) {
            java.sql.Date d = resultSet.getDate(3);
            String startTime = resultSet.getString(2);
            String endTime = resultSet.getString(1);
            Date d1 = convertFromSQLDateToJAVADate(d);
            valueMap.putIfAbsent((java.sql.Date) d1, new ArrayList<>());
            valueMap.get(d1).add(startTime);
            valueMap.get(d1).add(endTime);
            valueMap.get(d1).add("badminton");
        }
        Set<java.sql.Date> s;
        s = valueMap.keySet();
        dateList.addAll(s);
        logger.info("array" + dateList);
        int max = latestStreak((ArrayList<Date>) dateList);
        return max;

    }
}
