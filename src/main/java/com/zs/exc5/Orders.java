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

    PreparedStatement stmt = null;
    ResultSet rs;
    static Scanner scan = new Scanner(System.in);

    String orderId;
    String sourceAd;
    String destinationAd;
    String userId;
    String pId;
    String pinCode;
    String orderDate;

    private final Logger logger = LogImplement.getLog();


    /**
     * This function is giving the choice of insert,update,delete and select operations on order table.
     *
     * @throws SQLException Throwing exceptions.
     */
    public void orderTable(Connection c) throws SQLException {
        int chOpertion;
        logger.info("1.Insert 2.Update 3.Delete 4.Select 5.lastOrder 6.topOrder 7.lastDays 8.exit ");
        logger.info("Enter your choice of operation");
        chOpertion = scan.nextInt();

        switch (chOpertion) {
            case 1:
                insertOrder(c);
                break;
            case 2:
                updateOrder(c);
                break;
            case 3:
                deleteOrder(c);
                break;
            case 4:
                selectOrder(c);
                break;
            case 5:
                lastOrder(c);
                break;
            case 6:
                topOrder(c);
                break;
            case 7:
                lastDays(c);
                break;
            case 8:
                System.exit(0);
            default:
                logger.info("please enter correct choice");
        }
    }

    /**
     * This method is finding the last order details for a particular pinCode.
     *
     * @throws SQLException Throwing SQLException
     */
    public void lastDays(Connection c) throws SQLException {



        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
         String pinCode= scan.next();
        String str = "select * from orders_detail where pincode=? AND orderdate>='strDate'::date order by orderdate desc;";
        stmt = c.prepareStatement(str);
        stmt.setString(1,pinCode);
        rs = stmt.executeQuery();
        while (rs.next())
            logger.info(rs.getString(1) + "\t" + rs.getDate(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
    }

    /**
     * This fuction is displaying the details of top orders and products  for a particular user.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void topOrder(Connection c) throws SQLException {
        logger.info("Enter userId");
        String uId = scan.next();
        logger.info("Enter value of x");
        int x = scan.nextInt();
        String str = "select od.orderid,od.userid,po.productid,p.productname from orders_detail as od INNER JOIN place_orders po on od.orderid=po.orderid INNER JOIN products p ON po.productid=p.productid where userid = ? order by orderdate desc  limit ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, uId);
        stmt.setInt(2, x);
        rs = stmt.executeQuery();
        while (rs.next()) {
            logger.info(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
        }
    }

    /**
     * This fuction is displaying the details of last orders and products  for a particular user.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void lastOrder(Connection c) throws SQLException {
        logger.info("Enter userId");
        String uId = scan.next();
        logger.info("Enter value of x");
        int x = scan.nextInt();
        String str = "select * from orders_detail where userid = ? order by orderdate desc limit  ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, uId);
        stmt.setInt(2, x);
        rs = stmt.executeQuery();
        while (rs.next()) {

            logger.info(rs.getString(1) + "\t" + rs.getDate(2) + "\t" + rs.getString(3) + "\t" + rs.getString(6));
        }


    }

    /**
     * This function is updating data in order details.
     *
     * @throws SQLException Throwing Exceptions.
     */
    public void updateOrder(Connection c) throws SQLException {
        String feild1;
        String feild2;
        String val1;
        String val2;
        logger.info("enter feild to be updated and field have condtion");
        feild1 = scan.next();
        feild2 = scan.next();
        logger.info("Enter new value and value have condition ");
        val1 = scan.next();
        val2 = scan.next();
        String str = "UPDATE orders_detail SET " + feild1 + "= ?  FROM place_orders WHERE orders_detail.orderid=place_orders.orderid AND orders_detail." + feild2 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val1);
        stmt.setString(2, val2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("Successfully Updated");
        else
            logger.info("Not Successfully Updated");

    }

    /**
     * This function is deleting data from order table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void deleteOrder(Connection c) throws SQLException {
        String feild1, val;
        logger.info("Enter feild and value making condition to delete");
        feild1 = scan.next();
        val = scan.next();
        String str = "delete from orders_detail using  place_orders where orders_detail.orderid=place_orders.orderid AND orders_detail." + feild1 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("Successfully Deleted");
        else
            logger.info("Not Successfully Deleted");

    }

    /**
     * This function is displaying data of Order table.
     */
    public void selectOrder(Connection c) throws SQLException {
        String str = "select od.orderid ,userid,productid,source_address,destination_address from orders_detail od INNER JOIN place_orders po ON od.orderid=po.orderid ;";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        while (rs.next()) {

            logger.info(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
        }
    }

    /**
     * This method is inserting product information in order table.
     *
     * @throws SQLException Throwing SQLExceptions.
     */
    public void insertOrder(Connection c) throws SQLException {
        int numberProduct;
        logger.info(" ID Date Soucead Destinationad Pincode Uid");
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
            logger.info("Not successfully Inserted");
            return;
        }
        logger.info("Enter number of products purchased in this order");
        numberProduct = scan.nextInt();
        for (int i = 0; i < numberProduct; i++) {
            pId = scan.next();
            str = "INSERT INTO place_orders VAlUES (?,?);";
            stmt = c.prepareStatement(str);
            stmt.setString(1, orderId);
            stmt.setString(2, pId);
            result = stmt.executeUpdate();
            if (result == 1)
                logger.info("Successfully Inserted");
            else
                logger.info("Not Successfully Inserted");
        }

    }
}
