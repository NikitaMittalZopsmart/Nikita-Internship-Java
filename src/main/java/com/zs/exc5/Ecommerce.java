package main.java.com.zs.exc5;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is to create an Ecommerce application.
 */
public class Ecommerce {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int ch;
        Logger logger = LogImplement.getLog();
        ConnectionDB connectionObj = new ConnectionDB();
        Connection c = connectionObj.connection();
        do {
            logger.info("1.user 2.Products  3.Orders 4.Exit");
            logger.info("Enter you choice of table");
            ch = scan.nextInt();

            switch (ch) {
                case 1:
                    new User().userTable(c);
                    break;
                case 2:
                    new Product().productTable(c);
                    break;
                case 3:
                    new Orders().orderTable(c);
                    break;
                case 4:
                    System.exit(0);
                    return;
                default:
                    logger.info("Please enter a correct choice");

            }

        } while (ch < 4);
        connectionObj.closeConnection();
    }
}


