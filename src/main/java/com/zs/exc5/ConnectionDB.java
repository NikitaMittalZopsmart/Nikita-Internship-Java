package main.java.com.zs.exc5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionDB {
    private Connection c = null;
    private static final Logger logger = LogImplement.getLog();


    /**
     * Connecting  with database.
     *
     * @return A connection object.
     */

    public Connection connection() {
        try {
            logger.info("in connection");
            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/nikitadatabas",
                    "nikitaintern", "nikita");
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        logger.info("Database open successfully");

        return c;
    }

    public void closeConnection() throws SQLException {
        c.close();
    }
}
