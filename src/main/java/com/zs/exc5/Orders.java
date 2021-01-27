package main.java.com.zs.exc5;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is created to perform the operations in Order table.
 */
public class Orders {
    Connection c = null;
    PreparedStatement stmt = null;
    ResultSet rs;
    static Scanner scan = new Scanner(System.in);

    String orderId, sourceAd, destinationAd, userId, pId, pinCode, orderDate;

    private static Logger logr;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/Myproject/src/logger/logging.properties");

        logr = Logger.getLogger(Ecommerce.class.getName());
    }
    /**
     * To create a database connection.
     */
    public void connection(){

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/postgres",
                    "postgres", "root123");
        } catch (Exception e) {
            logr.info(e.getMessage());
        }
        logr.info("Database open successfully");

    }
    /**
     * This function is giving the choice of insert,update,delete and select operations on order table.
     * @throws SQLException
     */
    public void orderTable() throws SQLException{
        int chopertion;
        logr.info("1.Insert 2.Update 3.Delete 4.Select 5.lastOrder 6.topOrder 7.lastDays 8.exit ");
        logr.info("Enter your choice of operation");
        chopertion = scan.nextInt();
        connection();
        switch (chopertion) {
            case 1:
                insertOrder();
                break;
            case 2:
                updateOrder();
                break;
            case 3:
                deleteOrder();
                break;
            case 4:
                selectOrder();
                break;
            case 5:
                lastOrder();
                break;
            case 6:
                topOrder();
                break;
            case 7:lastDays();
                   break;
            case 8: System.exit(0);
            default:
                logr.info("please enter correct choice");
        }
    }

    /**
     * This method is fiding the last order details for a particular pincode.
     * @throws SQLException Throwing SQLException
     */
    public void lastDays() throws SQLException {
        LocalDate today = LocalDate.now();
        int x;
        logr.info("Enter value of x");
        x= scan.nextInt();
        LocalDate pre=today.plusDays(-x);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(pre);
        String str="select * from orders_detail where pincode='201345'AND orderdate>='strDate'::date order by orderdate desc;";
        stmt=c.prepareStatement(str);
        rs=stmt.executeQuery();
        while(rs.next())
            logr.info(rs.getString(1)+"\t"+rs.getDate(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
    }

    /**
     * This fuction is displaying the details of top orders and products  for a particular user.
     * @throws SQLException Throwing SQLException.
     */
    public void topOrder() throws SQLException {
        logr.info("Enter userId");
        String uid = scan.next();
        logr.info("Enter value of x");
        int x = scan.nextInt();
        String str = "select od.orderid,od.userid,po.productid,p.productname from orders_detail as od INNER JOIN place_orders po on od.orderid=po.orderid INNER JOIN products p ON po.productid=p.productid where userid = ? order by orderdate desc  limit ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, uid);
        stmt.setInt(2, x);
        rs = stmt.executeQuery();
        while (rs.next()) {
            logr.info(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
        }
    }
    /**
     * This fuction is displaying the details of last orders and products  for a particular user.
     * @throws SQLException Throwing SQLException.
     */
    public void lastOrder() throws SQLException {
        logr.info("Enter userId");
        String uid = scan.next();
        logr.info("Enter value of x");
        int x = scan.nextInt();
        String str = "select * from orders_detail where userid = ? order by orderdate desc limit  ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, uid);
        stmt.setInt(2, x);
        rs = stmt.executeQuery();
        while (rs.next()) {

            logr.info(rs.getString(1) + "\t" + rs.getDate(2) + "\t" + rs.getString(3) + "\t" + rs.getString(6));
        }


    }

    /**
     * This function is updating data in order details.
     * @throws SQLException
     */
    public void updateOrder() throws SQLException {
        String feild1, feild2;
        String val1, val2;
        logr.info("enter feild to be updated and field have condtion");
        feild1 = scan.next();
        feild2 = scan.next();
        logr.info("Enter new value and value have condition ");
        val1 = scan.next();
        val2 = scan.next();
        String str = "UPDATE orders_detail SET " + feild1 + "= ?  FROM place_orders WHERE orders_detail.orderid=place_orders.orderid AND orders_detail." + feild2 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val1);
        stmt.setString(2, val2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("Successfully Updated");
        else
            logr.info("Not Successfully Updated");

    }
    /**
     *   This function is deleting data from order table.
     * @throws SQLException Throwing SQLException.
     */
    public void deleteOrder() throws SQLException {
        String feild1, val;
        logr.info("Enter feild and value making condition to delete");
        feild1 = scan.next();
        val = scan.next();
        String str = "delete from orders_detail using  place_orders where orders_detail.orderid=place_orders.orderid AND orders_detail." + feild1 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("Successfully Deleted");
        else
            logr.info("Not Successfully Deleted");

    }
    /**
     *   This function is displaying data of Order table.
     */
    public void selectOrder() throws SQLException {
        String str = "select od.orderid ,userid,productid,source_address,destination_address from orders_detail od INNER JOIN place_orders po ON od.orderid=po.orderid ;";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        while (rs.next()) {

            logr.info(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
        }
    }
    /**
     * This method is inserting product information in order table.
     * @throws SQLException Throwing SQLExceptions.
     */
    public void insertOrder() throws SQLException {
        int numberProduct;
        logr.info(" ID Date Soucead Destinationad Pincode Uid");
        orderId = scan.next();
        orderDate = scan.next();
        sourceAd = scan.next();
        destinationAd = scan.next();
        pinCode = scan.next();
        userId = scan.next();
        LocalDate localDate = LocalDate.parse(orderDate);
        String str = "insert into orders_detail values (?,?,?,?,?,?);";
        stmt = c.prepareStatement(str);
        stmt.setString(1, orderId);
        stmt.setDate(2, Date.valueOf(localDate));
        stmt.setString(3, destinationAd);
        stmt.setString(4, sourceAd);
        stmt.setString(5, pinCode);
        stmt.setString(6, userId);
        int result = stmt.executeUpdate();
        if (result != 1) {
            logr.info("Not successfully Inserted");
            return;
        }
        logr.info("Enter number of products purchased in this order");
        numberProduct = scan.nextInt();
        for (int i = 0; i < numberProduct; i++) {
            pId = scan.next();
            str = "INSERT INTO place_orders VAlUES (?,?);";
            stmt = c.prepareStatement(str);
            stmt.setString(1, orderId);
            stmt.setString(2, pId);
            result = stmt.executeUpdate();
            if (result == 1)
                logr.info("Successfully Inserted");
            else
                logr.info("Not Successfully Inserted");
        }

    }
}
