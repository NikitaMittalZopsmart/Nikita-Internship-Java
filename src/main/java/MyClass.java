package java;
import java.util.logging.Logger;

/**
 * Using logger
 */
public class MyClass {
    private static Logger LOGGER;

        static {
            System.setProperty("java.util.logging.config.file",
                    "src/resources/logging.properties");
            //must initialize loggers after setting above property
            LOGGER = Logger.getLogger(MyClass.class.getName());
        }

        public static void main(String[] args) {
            System.out.println("-- main method starts --");
            LOGGER.info("an info msg");
            LOGGER.warning("a warning msg");
            LOGGER.severe("a severe msg");
        }
}
