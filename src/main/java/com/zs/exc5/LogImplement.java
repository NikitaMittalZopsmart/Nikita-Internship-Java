package main.java.com.zs.exc5;

public class LogImplement {
    private static java.util.logging.Logger logr;

    /**
     * This is returning a logger object.
     *
     * @return Returning a logger object.
     */
    public static java.util.logging.Logger getLog() {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/Myproject/src/logger/logging.properties");

        logr = java.util.logging.Logger.getLogger(Ecommerce.class.getName());
        return logr;
    }
}
