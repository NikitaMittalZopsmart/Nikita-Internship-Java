package com.zs.HobbiesProject.MainStart;

import java.util.logging.Logger;

/**
 * This class is created to throw custom exceptions.
 */
public class CustomException extends Throwable {
    /**
     * This method is throwing exception when length of startPoint is greater than 30.
     * @param logger A Logger Object.
     */

    public void startPointException(Logger logger)
    {
        logger.warning("Start Point must be less than 8");
        logger.info("Enter valid start point again");
    }

    /**
     * This method is throwing exception when length of endPoint is greater than 30.
     * @param logger A Logger Object.
     */
    public void endPointException(Logger logger)
    {
        logger.warning("End point must be less than 8");
        logger.info("Enter valid end point again");
    }
    public void connectionException(Logger logger){
        logger.warning("404-No connection to database");
    }


}
