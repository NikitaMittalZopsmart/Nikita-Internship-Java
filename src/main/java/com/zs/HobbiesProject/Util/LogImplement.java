package com.zs.HobbiesProject.Util;

/**
 * This class is used to make a logger object to implment logging.
 */
public class LogImplement {
    private static java.util.logging.Logger logger;

    /**
     * This is returning a logger object.
     *
     * @return Returning a logger object.
     */
    public static java.util.logging.Logger getLog() {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/zs-java-internship-nikita/src/main/resources/logging.properties");
        try{
        logger = java.util.logging.Logger.getLogger(LogImplement.class.getName());}
        catch (Exception e){e.getMessage();}

        return logger;
    }
}
