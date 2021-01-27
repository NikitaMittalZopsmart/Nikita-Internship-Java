package main.java.com.zs.HobbiesProject.DAO;


import main.java.com.zs.HobbiesProject.MainStart.LRUMain;
import main.java.com.zs.HobbiesProject.Model.Badminton;
import main.java.com.zs.HobbiesProject.Util.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class BadmintonImp implements BadmintonInterface{

    private PreparedStatement insertStament;
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
    private static java.sql.Date convertUtilToSql(Date uDate) {
        return (new java.sql.Date(uDate.getTime()));
    }

    /**
     * This funtion is converting a date from java.sql to java.util .
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
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements1() throws SQLException {
        c = cObj.connection();
        insertStament = c.prepareStatement("insert into badminton values(?,?,?,?,?,?);");
    }

    /**
     * This function is used to create a preparedStatement to fetch the travel table dat for a particular user. in travel table.
     * @throws SQLException Throwing SQLException.
     */
    public void prepareStatements2() throws SQLException {
        c = cObj.connection();
        stmt2 = c.prepareStatement("select * from badminton where user_id=? order by hobby_date ;");
    }

    /**
     * This method is created to insert a record in badminton table.
     * @param bObj An object of travel class.
     * @param logger A logger Object.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void create(Badminton bObj, Logger logger) throws SQLException {

        logger.info("enter user id");
        prepareStatements1();
        uid = scan.next();
        insertStament.setDate(1, convertUtilToSql(bObj.getEndTime()));
        insertStament.setDate(2, convertUtilToSql(bObj.getStartTime()));
        insertStament.setDate(3, convertUtilToSql(bObj.getTickDate()));
        insertStament.setInt(4, bObj.getNumberOfMove());
        insertStament.setString(5, bObj.getResult());

        insertStament.setString(6, uid);
        int m = insertStament.executeUpdate();
        if (m == 1)
            logger.info("successfully inserted");
        else
            logger.info("not inserted");
    }

    /**
     * This method is created to calculate the latest streak for badminton hobby for a particular user.
     * @param arr An arraylist have dates in a particular order.
     * @param logger A logger Object.
     * @param uidInput User Id for which we are finding lateststreak.
     * @throws SQLException Throwing SQLException.
     */
    @Override
    public void latestStreak(ArrayList<Date> arr, Logger logger, String uidInput, LRUMain lruObj) throws SQLException {
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
        lruObj.putInCache(uidInput,logger,"badminton",max);

    }

    /**
     * This method is to get the dates for badminton hobby for a particular object.
     * @param uidInput User Id for which we are finding latest streak.
     * @param logger A logger Object.
     * @param ch A integer to decide which method is to call among lateststreak and loneststreak.
     * @throws SQLException Throwing SQLExceptions.
     */
    @Override
    public void streak(String uidInput, Logger logger, int ch,LRUMain lruObj) throws SQLException {

        if(lruObj.getValue(uidInput,logger,"badminton"))
            return ;
        logger.info("In streak");
        List<Date> arr = new ArrayList<>();
        prepareStatements2();
        stmt2.setString(1, uidInput);
        rs = stmt2.executeQuery();
        TreeMap<java.sql.Date, ArrayList<String>> valueMap = new TreeMap<>();
        valueMap.clear();
        while (rs.next()) {
            java.sql.Date d = rs.getDate(3);
            String startTime = rs.getString(2);
            String endTime = rs.getString(1);
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
        if (ch == 2)
            latestStreak((ArrayList<Date>) arr, logger,uidInput,lruObj);


    }
}
