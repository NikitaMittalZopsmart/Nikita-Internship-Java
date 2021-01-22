package main.java.com.zs.logger;

import java.util.*;

public class Dictionary {
    private final Scanner scan = new Scanner(System.in);
    private String inputString;
    private static final java.util.logging.Logger loGr;


    private  final TreeMap<String, String> ham = new TreeMap<>();

    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/zs-java-internship-nikita/src/main/resources/logging.properties");

        loGr = java.util.logging.Logger.getLogger(Dictionary.class.getName());

    }

    public void insert() {
        String meaning;
        loGr.info("Enter a string");
        inputString = scan.next();
        inputString = inputString.toLowerCase(Locale.ROOT);
        loGr.info("Enter meaning");
        meaning = scan.next();
        if (ham.containsKey(inputString))
            loGr.info("string is present");
        else {
            ham.put(inputString, meaning);
        }
    }

    public void search() {
        loGr.info("Enter a string searchString");
        inputString = scan.next();
        if (ham.containsKey(inputString))
            loGr.info("string is present");
        else
            loGr.info("This word is not present");

    }

    public void meaning() {
        loGr.info("Enter a string");
        inputString = scan.next();
        inputString = inputString.toLowerCase(Locale.ROOT);
        if (ham.isEmpty()) {
            loGr.info("Dictionary is empty");

        } else {
            if (ham.containsKey(inputString))
                loGr.info(ham.getOrDefault(inputString,"No Found"));
            else
                loGr.info("This Word is not present in dictionary");
        }

    }

    public void display() {
        Iterator hmIterator = ham.entrySet().iterator();
        loGr.info("dictionary");
        if (ham.isEmpty()) {
            loGr.info("Dictionary is empty");

        }
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();

            loGr.info(mapElement.getKey() + " : " + mapElement.getValue());
        }
    }

    public void startingWith() {
        loGr.info("Enter a string from where to start search");
        inputString = scan.next();
        Map<String, String> mp = ham.tailMap(inputString);
        Set<String> arl = mp.keySet();
        for (String str : arl) {
            if (str.startsWith(inputString))
                loGr.info(str);
        }
    }

    public void similar() {
        loGr.info("Enter a word whose similar is to be display");
        inputString = scan.next();

        int difCounter;
        Set<String> arl2 = ham.keySet();
        for (String str : arl2) {
            difCounter = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != inputString.charAt(j))
                    difCounter++;
                if (difCounter > 1)
                    break;

            }
            if (difCounter <= 1)
                loGr.info(str);


        }

    }

    public void check() {
        int ch;
        do{

            loGr.info("1.insert 2.Search 3.Meaning 4.statingWith 5.Similar 6.Display 7.Exit");
            loGr.info("enter your cho1ice");
            ch = scan.nextInt();
            switch (ch) {
                case 1:
                    insert();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    meaning();
                    break;
                case 4:
                    startingWith();
                    break;
                case 5:
                    similar();
                    break;
                case 6:
                    display();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    loGr.info("please enter the correct choice");
                    break;
            }
        }while(ch<=6);
    }

    public static void main(String[] args) {
        new Dictionary().check();

    }


}


