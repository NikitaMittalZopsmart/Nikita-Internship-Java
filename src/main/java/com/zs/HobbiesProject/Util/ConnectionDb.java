package com.zs.HobbiesProject.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This class is developed to create a connection with database.
 */
public class ConnectionDb {
    private Connection connection = null;
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

            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/nikitadatabas",
                    "nikitaintern", "nikita");

        } catch (Exception e) {
            logger.info(e.getMessage());

        }

        logger.info("Database open successfully");

        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
