package com.zs.logger;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This class is the starting class for this dictionary.
 */
public class Start {
    private static final java.util.logging.Logger logger;
    private final static Scanner scan = new Scanner(System.in);
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/zs-java-internship-nikita/src/main/resources/logging.properties");

        logger = java.util.logging.Logger.getLogger(Dictionary.class.getName());

    }

    public static void main(String[] args) {
        int ch;
        SortedMap<String, String> dictionaryMap = new TreeMap<>();

        do {
            logger.info("1.insert 2.Search 3.Meaning 4.statingWith 5.Similar 6.Display 7.Exit");
            logger.info("enter your cho1ice");
            ch = scan.nextInt();
            switch (ch) {
                case 1:
                     new Dictionary().insert(logger,dictionaryMap);
                    break;
                case 2:
                    new Dictionary().search(logger,dictionaryMap);
                    break;
                case 3:
                    new Dictionary().meaning(logger,dictionaryMap);
                    break;
                case 4:
                    new Dictionary().startingWith(logger,dictionaryMap);
                    break;
                case 5:
                    new Dictionary().similar(logger,dictionaryMap);
                    break;
                case 6:
                    new Dictionary().display(logger,dictionaryMap);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    logger.info("please enter the correct choice");
                    break;
            }
        } while (ch <= 7);
    }
}
