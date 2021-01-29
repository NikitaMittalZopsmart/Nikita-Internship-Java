package com.zs.HobbiesProject.DAO;


import com.zs.HobbiesProject.MainStart.LRUMain;
import com.zs.HobbiesProject.Model.Travel;
import com.zs.HobbiesProject.Util.ConnectionDb;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;

/**
 * This class is implementing Travel Interface to perform database operations.
 */
public class TravelImp implements TravelInterface {

    private PreparedStatement insertStatement;
    private PreparedStatement selectStatement;
    private Connection connection;
    private ConnectionDb connectionObject =new ConnectionDb();
    String uid;
    ResultSet resultSet;

    Scanner scan = new Scanner(System.in);

    /**
     * This function is converting a date from java.util to java.sql format.
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
     * This function is  creating a preparedStatement for inserting values in travel table.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements1() throws SQLException {
        connection = connectionObject.connection();
        insertStatement = connection.prepareStatement("insert into travel values(?,?,?,?,?,?,?);");
    }

    /**
     * This function is creating a preparedStatement to fetch the travel table data for a particular user.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements2() throws SQLException {
        connection = connectionObject.connection();
        selectStatement = connection.prepareStatement("select * from travel where user_id=? order by hobby_date ;");
    }

    /**
     * This method is created to insert a record in travel table.
     * @param travelObject An object of travel class.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void create(Travel travelObject, Logger logger) throws SQLException {

        logger.info("enter user id");
        prepareStatements1();
        uid = scan.next();
        insertStatement.setDate(1, convertUtilToSql(travelObject.getEndTime()));
        insertStatement.setDate(2, convertUtilToSql(travelObject.getStartTime()));
        insertStatement.setDate(3, convertUtilToSql(travelObject.getTickDate()));
        insertStatement.setString(4, travelObject.getStartingPoint());
        insertStatement.setString(5, travelObject.getEndPoint());
        insertStatement.setInt(6, (int) travelObject.getDistance());
        insertStatement.setString(7, uid);
        int m = insertStatement.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
    }

    /**
     * This method is created to calculate the latest streak  for a particular user having travelling hobby.
     * @param dateList An arraylist have travelling dates  in a particular order.
     * @param logger A logger Object.
     * @param uidInput User Id for which we are finding latest streak.
     * @throws SQLException Throwing SQLException.
     */
    @Override
    public void latestStreak(ArrayList<java.util.Date> dateList, Logger logger, String uidInput, LRUMain lruObject) throws SQLException {
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
        lruObject.putInCache(uidInput,logger,"travel",max);

    }

    /**
     * This method is to get the dates for travel hobby for a particular object.
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger A logger Object.
     * @param choice A integer to decide which method is to call among latest streak.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void streak(String uidInput, Logger logger, int choice, LRUMain lruObject) throws SQLException {

       if(lruObject.getValue(uidInput,logger,"travel"))
           return ;
        logger.info("In streak");
        List<java.util.Date> arr = new ArrayList<>();
        prepareStatements2();
        selectStatement.setString(1, uidInput);
        resultSet = selectStatement.executeQuery();
        TreeMap<Date, ArrayList<String>> valueMap = new TreeMap<>();
        valueMap.clear();
        while (resultSet.next()) {
            Date d = resultSet.getDate(3);
            String startTime = resultSet.getString(1);
            String endTime = resultSet.getString(1);
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
        if (choice == 2)
            latestStreak((ArrayList<java.util.Date>) arr, logger,uidInput, lruObject);


    }
}



