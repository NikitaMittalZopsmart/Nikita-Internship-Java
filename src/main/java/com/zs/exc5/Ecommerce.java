package main.java.com.zs.exc5;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class is to create an Ecommerce application.
 */
public class Ecommerce {

    private static Scanner scan=new Scanner(System.in);
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        int ch;
        Logger loGr=LogImplement.getLog();
        do {
            loGr.info("1.user 2.Products  3.Orders 4.Exit");
            loGr.info("Enter you choice of table");
            ch = scan.nextInt();

            switch (ch) {
                case 1:
                    new User().userTable();
                    break;
                case 2:
                    new Product().productTable();
                    break;
                case 3:
                    new Orders().orderTable();
                    break;
                case 4:
                    System.exit(0);
                default:
                    loGr.info("Please enter a correct choice");
            }

        }while(ch<4);
    }
}


