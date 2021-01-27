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
    private Connection c = null;
    private PreparedStatement stmt = null;
    private ResultSet rs;
    private String pid, pname;
    int pquantity, pval;
    Scanner scan = new Scanner(System.in);
    private static Logger logr;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/Myproject/src/logger/logging.properties");

        logr = Logger.getLogger(Ecommerce.class.getName());
    }
    /**
     * To create a database connection.
     */
    public void connection() throws ClassNotFoundException, SQLException {

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
     * This function is giving the choice of insert,update,delete and select operations on product table.
     * @throws SQLException
     */
    public void productTable() throws SQLException, ClassNotFoundException {
        int chopertion;
        logr.info("1.Insert 2.Update 3.Delete 4.Select 5.ProductCatalog 6.exit ");
        logr.info("Enter your choice of operation");
        chopertion = scan.nextInt();
        connection();
        switch (chopertion) {
            case 1:
                insertProduct();
                break;
            case 2:
                updateProduct();
                break;
            case 3:
                deleteProduct();
                break;
            case 4:
                selectProduct();
                break;
            case 5:
                   productCatalog();
                   break;
            case 6:    System.exit(0);
            default:
                logr.info("Please enter correct choice");
        }
    }

    /**
     * This metod is displaying product table data.
     * @throws SQLException
     */
    public void productCatalog() throws SQLException {
        Set<Product> s=new LinkedHashSet<>();
        String str = "select * from products";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        System.out.println("In product catalog");
        while(rs.next())
        {
            Product pObj=new Product();
            pObj.pquantity=rs.getInt(1);
            pObj.pval=rs.getInt(2);
            pObj.pid=rs.getString(3);
            pObj.pname=rs.getString(4);
            s.add(pObj);
        }
        logr.info("ProductId   Productname   Productvalue   ProductQuantity");
        for(Product p:s)
            logr.info(p.pid+"\t\t\t"+p.pname+"\t\t\t"+p.pval+"\t\t\t"+p.pquantity);
    }

    /**
     * This method is inserting product information in product table.
     * @throws SQLException Throwing SQLExceptions.
     */
    public void insertProduct() throws SQLException {
        logr.info("Id Name Quantity value");
        pid = scan.next();
        pname = scan.next();
        pquantity = scan.nextInt();
        pval = scan.nextInt();
        String str = "INSERT INTO products VAlUES (?,?,?,?);";
        stmt = c.prepareStatement(str);
        stmt.setInt(1, pquantity);
        stmt.setInt(2, pval);
        stmt.setString(3, pid);
        stmt.setString(4, pname);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("successfully Inserted");
        else
            logr.info("Not Successfully Inserted");

    }
    /**
     *   This function is updating data in product table.
     * @throws SQLException Throwing SQLException.
     */
    public void updateProduct() throws SQLException {
        String feild1, feild2;
        logr.info("Enter field to be updated and condition feild");
        feild1 = scan.next();
        feild2 = scan.next();
        logr.info("enter new value and condition value");
        String newval = scan.next();
        String newval2 = scan.next();
        String str = "UPDATE  products SET " + feild1 + "= ? WHERE " + feild2 + "= ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, newval);
        stmt.setString(2, newval2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("successfully Updated");
        else
            logr.info("Not Successfully Updated");
    }
    /**
     *   This function is deleting data from Product table.
     * @throws SQLException Throwing SQLException.
     */
    public void deleteProduct() throws SQLException {
        String feild1, val;
        logr.info("Enter field and value making condition to delete");
        feild1 = scan.next();
        val = scan.next();
        String str = "delete from products where " + feild1 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("Successfully Deleted");
        else
            logr.info("Not Successfully Deleted");
    }
    /**
     *   This function is displaying data of Product table.
     */
    public void selectProduct() throws SQLException {
        String str = "select * from products";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        logr.info("Value   ProductID   Quantity  Name");
        while (rs.next())
            logr.info(rs.getInt(2) + "\t" + rs.getString(3) + "\t\t\t" + rs.getInt(1) + "\t" + rs.getString(4));

    }
}

