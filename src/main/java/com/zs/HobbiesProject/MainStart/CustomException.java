package main.java.com.zs.HobbiesProject.MainStart;

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
        logger.warning("Start Point must be less than 30");
    }

    /**
     * This method is throwing exception when length of endPoint is greater than 30.
     * @param logger A Logger Object.
     */
    public void endPointException(Logger logger)
    {
        logger.warning("End point must be less than 30");
    }

    /**
     * This method is throwing exception when length of title is greater than 100.
     * @param logger  A Logger Object.
     */
    public void titleException(Logger logger)
    {
        logger.warning("Title length is exceeding 100");
    }

}
