package main.java.com.zs.exc5;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class is created to perform the operations in product table.
 */
public class Product {

    private PreparedStatement stmt = null;
    private ResultSet rs;
    private String pId;
    String pName;
    int pQuantity, pVal;
    Scanner scan = new Scanner(System.in);
    private static final Logger logger = LogImplement.getLog();


    /**
     * This function is giving the choice of insert,update,delete and select operations on product table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void productTable(Connection c) throws SQLException{
        int chOpertion;
        logger.info("1.Insert 2.Update 3.Delete 4.Select 5.ProductCatalog 6.exit ");
        logger.info("Enter your choice of operation");
        chOpertion = scan.nextInt();

        switch (chOpertion) {
            case 1:
                insertProduct(c);
                break;
            case 2:
                updateProduct(c);
                break;
            case 3:
                deleteProduct(c);
                break;
            case 4:
                selectProduct(c);
                break;
            case 5:
                productCatalog(c);
                break;
            case 6:
                System.exit(0);
            default:
                logger.info("Please enter correct choice");
        }
    }

    /**
     * This method is displaying product table data.
     *
     * @throws SQLException
     */
    public void productCatalog(Connection c) throws SQLException {
        Set<Product> s = new LinkedHashSet<>();
        String str = "select * from products";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();

        while (rs.next()) {
            Product pObj = new Product();
            pObj.pQuantity = rs.getInt(1);
            pObj.pVal = rs.getInt(2);
            pObj.pId = rs.getString(3);
            pObj.pName = rs.getString(4);
            s.add(pObj);
        }
        logger.info("ProductId   Productname   Productvalue   ProductQuantity");
        for (Product p : s)
            logger.info(p.pId + "\t\t\t" + p.pName + "\t\t\t" + p.pVal + "\t\t\t" + p.pQuantity);
    }

    /**
     * This method is inserting product information in product table.
     *
     * @throws SQLException Throwing SQLExceptions.
     */
    public void insertProduct(Connection c) throws SQLException {
        logger.info("Id Name Quantity value");
        pId = scan.next();
        pName = scan.next();
        pQuantity = scan.nextInt();
        pVal = scan.nextInt();
        String str = "INSERT INTO products VAlUES (?,?,?,?);";
        stmt = c.prepareStatement(str);
        stmt.setInt(1, pQuantity);
        stmt.setInt(2, pVal);
        stmt.setString(3, pId);
        stmt.setString(4, pName);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("successfully Inserted");
        else
            logger.info("Not Successfully Inserted");

    }

    /**
     * This function is updating data in product table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void updateProduct(Connection c) throws SQLException {
        String feild1;
        String feild2;
        logger.info("Enter field to be updated and condition feild");
        feild1 = scan.next();
        feild2 = scan.next();
        logger.info("enter new value and condition value");
        String newValue = scan.next();
        String newValue2 = scan.next();
        String str = "UPDATE  products SET " + feild1 + "= ? WHERE " + feild2 + "= ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, newValue);
        stmt.setString(2, newValue2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("successfully Updated");
        else
            logger.info("Not Successfully Updated");
    }

    /**
     * This function is deleting data from Product table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void deleteProduct(Connection c) throws SQLException {
        String feild1, val;
        logger.info("Enter field and value making condition to delete");
        feild1 = scan.next();
        val = scan.next();
        String str = "delete from products where " + feild1 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("Successfully Deleted");
        else
            logger.info("Not Successfully Deleted");
    }

    /**
     * This function is displaying data of Product table.
     */
    public void selectProduct(Connection c) throws SQLException {
        String str = "select * from products";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        logger.info("Value   ProductID   Quantity  Name");
        while (rs.next())
            logger.info(rs.getInt(2) + "\t" + rs.getString(3) + "\t\t\t" + rs.getInt(1) + "\t" + rs.getString(4));

    }
}

