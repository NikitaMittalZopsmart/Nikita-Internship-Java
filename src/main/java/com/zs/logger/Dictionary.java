package main.java.com.zs.logger;

import java.util.*;
import java.util.logging.Logger;

/**
 * This class is developed to implement logger in dictionary.
 */
public class Dictionary {
    private final Scanner scan = new Scanner(System.in);
    private String inputString;


    /**
     * This method is inserting word and meaning into dictionary.
     *
     * @param logger Logger Object
     */
    public void insert(Logger logger, SortedMap<String, String> dictionaryMap) {
        String meaning;
        logger.info("Enter a string");
        inputString = scan.next();
        inputString = inputString.toLowerCase(Locale.ROOT);
        logger.info("Enter meaning");
        meaning = scan.next();
        if (dictionaryMap.containsKey(inputString))
            logger.info("string is present already");
        else {
            dictionaryMap.put(inputString, meaning);
        }
    }

    /**
     * This method is searching that a particular word is present in the dictionary.
     *
     * @param logger Logger Object.
     */
    public void search(Logger logger, SortedMap<String, String> dictionaryMap) {
        logger.info("Enter a string searchString");
        inputString = scan.next();
        if (dictionaryMap.containsKey(inputString))
            logger.info("string is present");
        else
            logger.info("This word is not present");

    }

    /**
     * This method is displaying the meaning the of  a particular word.
     *
     * @param logger Logger Object.
     */
    public void meaning(Logger logger, SortedMap<String, String> dictionaryMap) {
        logger.info("Enter a string");
        inputString = scan.next();
        inputString = inputString.toLowerCase(Locale.ROOT);
        if (dictionaryMap.isEmpty()) {
            logger.info("Dictionary is empty");

        } else {
            if (dictionaryMap.containsKey(inputString))
                logger.info(dictionaryMap.getOrDefault(inputString, "No Found"));
            else
                logger.info("This Word is not present in dictionary");
        }

    }

    /**
     * This method is displaying the dictionary.
     *
     * @param logger Logger Object.
     */
    public void display(Logger logger, SortedMap<String, String> dictionaryMap) {
        Iterator dictionaryIterator = dictionaryMap.entrySet().iterator();
        logger.info("dictionary");
        if (dictionaryMap.isEmpty()) {
            logger.info("Dictionary is empty");

        }
        while (dictionaryIterator.hasNext()) {
            Map.Entry dictionaryElement = (Map.Entry) dictionaryIterator.next();

            logger.info(dictionaryElement.getKey() + " : " + dictionaryElement.getValue());
        }
    }

    /**
     * This method is displaying all words  of dictionary starting with a particular letter or word.
     *
     * @param logger Logger Object.
     */
    public void startingWith(Logger logger, SortedMap<String, String> dictionaryMap) {
        logger.info("Enter a string from where to start search");
        inputString = scan.next();
        Map<String, String> wordsStarting = dictionaryMap.tailMap(inputString);
        Set<String> arl = wordsStarting.keySet();
        for (String str : arl) {
            if (str.startsWith(inputString))
                logger.info(str);
        }
    }

    /**
     * This method is displaying the similar words of a word in dictionary.
     *
     * @param logger Logger Object.
     */
    public void similar(Logger logger, SortedMap<String, String> dictionaryMap) {
        logger.info("Enter a word whose similar is to be display");
        inputString = scan.next();
        int difCounter;
        Set<String> wordSet = dictionaryMap.keySet();
        for (String str : wordSet) {
            difCounter = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != inputString.charAt(j))
                    difCounter++;
                if (difCounter > 1)
                    break;
            }
            if (difCounter <= 1)
                logger.info(str);
        }
    }
}


