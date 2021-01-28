package main.java.com.zs.exc5;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is to perform the operations on User Table.
 */
public class User {

    PreparedStatement stmt = null;
    ResultSet rs;
    static Scanner scan = new Scanner(System.in);
    private static final Logger logger = LogImplement.getLog();


    /**
     * This function is giving the choice of insert,update,delete and select operations on user table.
     *
     * @throws SQLException Throwing Exception.
     */
    public void userTable(Connection c) throws SQLException {

        logger.info("1.Insert 2.Update 3.Delete 4.Select 5.exit ");
        logger.info("Enter your choice of operation");
        int chOpertion = scan.nextInt();

        switch (chOpertion) {
            case 1:
                insertUSer(c);
                break;
            case 2:
                updateUser(c);
                break;
            case 3:
                deleteUser(c);
                break;
            case 4:
                selectUser(c);
                break;

            case 5:
                System.exit(0);
        }

    }

    /**
     * This function is inserting data in user table.
     *
     * @throws SQLException Throw SQLException.
     */
    public void insertUSer(Connection c) throws SQLException {
        String id ;
        String email;
        String name ;
        String pass ;
        logger.info("Id Name Password Email");
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
            logger.info("successfully updated");
        else
            logger.info("Not Successfully Updated");

    }

    /**
     * This function is displaying data in user table.
     */

    void selectUser(Connection c) throws SQLException {
        String str = "select * from users";
        stmt = c.prepareStatement(str);
        rs = stmt.executeQuery();
        logger.info("Userid   UserName   Email      Password");
        while (rs.next())
            logger.info(rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(4) + "\t\t " + rs.getString(3));
    }

    /**
     * This function is deleting data from user table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void deleteUser(Connection c) throws SQLException {
        String feild1, value;
        logger.info("Enter field and value making condition to delete");
        feild1 = scan.next();
        value = scan.next();
        String str = "delete from users WHERE " + feild1 + " = ?;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, value);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("successfully deleted");
        else
            logger.info("Not successfully deleted");

    }

    /**
     * This function is updating data in user table.
     *
     * @throws SQLException Throwing SQLException.
     */
    public void updateUser(Connection c) throws SQLException {
        String feild1, feild2;
        logger.info("Enter field to be updated and condition feild");
        feild1 = scan.next();
        feild2 = scan.next();
        logger.info("enter new value and condition value");
        String newValue = scan.next();
        String newValue2 = scan.next();
        String str = "UPDATE users SET " + feild1 + " = ? WHERE " + feild2 + " = ? ;";
        stmt = c.prepareStatement(str);
        stmt.setString(1, newValue);
        stmt.setString(2, newValue2);
        int result = stmt.executeUpdate();
        if (result == 1)
            logger.info("successfully Updated");
        else
            logger.info("Not Successfully Updated");
    }
}



