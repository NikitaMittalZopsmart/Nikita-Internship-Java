package main.java.com.zs.exc5;

public class LogImplement {

    private LogImplement(){}

    /**
     * This is returning a logger object.
     *
     * @return Returning a logger object.
     */
    public static java.util.logging.Logger getLog() {
         java.util.logging.Logger logger;
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/zs-java-internship-nikita/src/main/resources/logging.properties");

        logger = java.util.logging.Logger.getLogger(Ecommerce.class.getName());
        return logger;
    }
}
