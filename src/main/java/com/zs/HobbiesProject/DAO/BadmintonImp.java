package com.zs.HobbiesProject.DAO;


import com.zs.HobbiesProject.MainStart.LRUMain;
import com.zs.HobbiesProject.Model.Badminton;
import com.zs.HobbiesProject.Util.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class BadmintonImp implements BadmintonInterface{

    private PreparedStatement insertStatement;
    private PreparedStatement fetchStatement;
    private Connection connection;

    private ConnectionDb connectionObj =new ConnectionDb();
    String uid;
    ResultSet resultSet;

    Scanner scan = new Scanner(System.in);

    /**
     * This funtion is converting a date from java.util to java.sql.
     * @param uDate A date in java.util format.
     * @return A date in java.sql format.
     */
    private static java.sql.Date convertUtilToSql(Date uDate) {
        return (new java.sql.Date(uDate.getTime()));
    }

    /**
     * This function is converting a date from java.sql to java.util  format.
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
     * This function is  creating a preparedStatement for inserting record in badminton table.
     * @throws SQLException Throwing SQLException.
     */
    public Connection prepareStatements1() throws SQLException {
        connection = connectionObj.connection();
        insertStatement = connection.prepareStatement("insert into badminton values(?,?,?,?,?,?);");
         return connection;
    }

    /**
     * This function is  creating a preparedStatement to fetch the badminton table data for a particular user.
     * @throws SQLException Throwing SQLException.
     */
    public Connection prepareStatements2() throws SQLException {
        connection = connectionObj.connection();
        fetchStatement = connection.prepareStatement("select * from badminton where user_id=? order by hobby_date ;");
        return connection;
    }

    /**
     * This method is created to insert a record in badminton table.
     * @param badmintonObject An object of travel class.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void create(Badminton badmintonObject, Logger logger) throws SQLException {

        logger.info("enter user id");
        Connection connection=prepareStatements1();
        uid = scan.next();
        insertStatement.setDate(1, convertUtilToSql(badmintonObject.getEndTime()));
        insertStatement.setDate(2, convertUtilToSql(badmintonObject.getStartTime()));
        insertStatement.setDate(3, convertUtilToSql(badmintonObject.getTickDate()));
        insertStatement.setInt(4, badmintonObject.getNumberOfMove());
        insertStatement.setString(5, badmintonObject.getResult());

        insertStatement.setString(6, uid);
        int m = insertStatement.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
        connection.close();
    }

    /**
     * This method is created to calculate the latest streak for badminton hobby for a particular user.
     * @param dateList An arraylist have dates in a particular order.
     * @param logger A logger Object.
     * @param uidInput User Id for which we are finding lateststreak.
     * @throws SQLException Throwing SQLException.
     */
    @Override
    public void latestStreak(ArrayList<Date> dateList, Logger logger, String uidInput, LRUMain lruObject) throws SQLException {
        logger.info("In lateststreak");
        int stIndex = 0;
        int endIndex;
        int max = 0;
        for (int j = 0; j < dateList.size() - 1; j++) {
            long noOfDaysBetween = (long) dateList.get(j + 1).getDate() - dateList.get(j).getDate();
            if (noOfDaysBetween == 1) {
                endIndex = j + 1;
                max = endIndex - stIndex;

            } else {
                stIndex = j + 1;
            }
        }
        logger.info("latestStreak"+max);
        lruObject.putInCache(uidInput,logger,"badminton",max);

    }

    /**
     * This method is to fetch the dates of a particular user having badminton as a hobby.
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger A logger Object.
     * @param choice A integer to decide which method is to call among lateststreak and loneststreak.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void streak(String uidInput, Logger logger, int choice, LRUMain lruObj) throws SQLException {

        if(lruObj.getValue(uidInput,logger,"badminton"))
            return ;
        logger.info("In streak");
        List<Date> arr = new ArrayList<>();
        Connection connection=prepareStatements2();
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
        arr.addAll(s);
        logger.info("array" + arr);
        if (choice == 2)
            latestStreak((ArrayList<Date>) arr, logger,uidInput,lruObj);


    }
}
