package main.java.com.zs.exc5;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is to perform the operations on User Table.
 */
public class User {
    Connection c = null;
    PreparedStatement stmt = null;
    ResultSet rs;
    static Scanner scan = new Scanner(System.in);
    private static Logger logr;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/Myproject/src/logger/logging.properties");

        logr = Logger.getLogger(Ecommerce.class.getName());
    }

    /**
     * To create a database connection.
     */
    public void connection() {

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
     * This function is giving the choice of insert,update,delete and select operations on user table.
     * @throws SQLException
     */
    public void userTable() throws SQLException {

        logr.info("1.Insert 2.Update 3.Delete 4.Select 5.exit ");
        logr.info("Enter your choice of operation");
        int chopertion = scan.nextInt();
        connection();
        switch (chopertion) {
            case 1:
                insertUSer();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                selectUser();
                break;

            case 5:
                System.exit(0);
        }
        c.close();
    }

    /**
     * This function is inserting data in user table.
     * @throws SQLException Throw SQLException.
     */
    public void insertUSer() throws SQLException {
        String id = "", email = "", name = "", pass = "";
        logr.info("Id Name Password Email");
        id = scan.next();
        name = scan.next();
        pass = scan.next();
        email = scan.next();
        String str = "INSERT INTO users VAlUES (?,?,?,?)";
        stmt = c.prepareStatement(str);
        stmt.setString(1, id);
        stmt.setString(2, name);
        stmt.setString(3, pass);
        stmt.setString(4, email);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("successfully updated");
        else
            logr.info("Not Successfully Updated");

    }

    /**
     *   This function is displaying data in user table.
     */

    void selectUser() throws SQLException {
        String str = "select * from users";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        logr.info("Userid   UserName   Email      Password");
        while (rs.next())
            logr.info(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(4) + "\t\t " + rs.getString(3));
    }

    /**
     *   This function is deleting data from user table.
     * @throws SQLException Throwing SQLException.
     */
    public void deleteUser() throws SQLException {
        String feild1, val;
        logr.info("Enter field and value making condition to delete");
        feild1 = scan.next();
        val = scan.next();
        String str = "delete from users WHERE " + feild1 + " = ?;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, val);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("successfully deleted");
        else
            logr.info("Not successfully deleted");

    }

    /**
     *   This function is updating data in user table.
     * @throws SQLException Throwing SQLException.
     */
    public void updateUser() throws SQLException {
        String feild1, feild2;
        logr.info("Enter field to be updated and condition feild");
        feild1 = scan.next();
        feild2 = scan.next();
        logr.info("enter new value and condition value");
        String newval = scan.next();
        String newval2 = scan.next();
        String str = "UPDATE users SET " + feild1 + " = ? WHERE " + feild2 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, newval);
        stmt.setString(2, newval2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logr.info("successfully Updated");
        else
            logr.info("Not Successfully Updated");
    }
}



