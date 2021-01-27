package main.java.com.zs.exc3;

import java.util.*;

/**
 * This class is to develope a dictionary.
 */
public class Dictionary {
    Scanner scan = new Scanner(System.in);
    String inputString ="" ;
    String meaning ="";
    int ch;
    String line = "";
    TreeMap<String, String> ham = new TreeMap<String, String>();

    //This Funtion is to insert the word and meaning into the dictionary.
    public void insert() {
        System.out.println("Enter a string");
        inputString = scan.next();
        System.out.println("Enter meaning");
        meaning = scan.next();
        if (ham.containsKey(inputString))
            System.out.println("string is present");
        else {
            ham.put(inputString, meaning);
        }
    }
    //This function is to search the the specific word that this word is present in dictionary.
    public void search() {
        System.out.println("Enter a string searchstring");
        inputString = scan.next();
        if (ham.containsKey(inputString))
            System.out.println("string is present");
        else
            System.out.println("This word is not present");

    }

    /**
     * This function is displaying the meaning of a particular given word.
     */

    public void meaning() {
        System.out.println("Enter a string");
        inputString = scan.next();
        if (ham.isEmpty()) {
            System.out.println("Dictionary is empty");

        } else {
            if (ham.containsKey(inputString))
                System.out.println(ham.get(inputString));
            else
                System.out.println("This Word is not present in dictionary");
        }

    }
    //This function is displaying the complete dictionary.
    public void display() {
        Iterator hmIterator = ham.entrySet().iterator();
        System.out.println("dictionary");
        if (ham.isEmpty()) {
            System.out.println("Dictionary is empty");

        }
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();

            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
        }
    }

    /**
     * This function is displaying all words stating with a particular string.
     */

    public void startingWith() {
        System.out.println("Enter a string from where to start search");
        inputString = scan.next();
        Map<String, String> mp = ham.tailMap(inputString);
        Set<String> arl = mp.keySet();
        for (String str : arl) {
            if (str.startsWith(inputString))
                System.out.println(str);
        }
    }

    /**
     * This function is displaying all the similar words to a specific word.
     */

    public void similar() {
        System.out.println("Enter a word whose similar is to be display");
        inputString = scan.next();
        int difcounter;
        Set<String> arl2 = ham.keySet();
        for (String str : arl2) {
            difcounter = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != inputString.charAt(j))
                    difcounter++;
                if (difcounter > 1)
                    continue;

            }
            if (difcounter <= 1)
                System.out.println(str);


        }

    }

    /**
     * This function is to call other functions.
     */

    public void check() {
        do{
            System.out.println("1.insert 2.Search 3.Meaning 4.statingwith 5.Similar 6.Display 7.Exit");
            System.out.println("enter your cho1ice");
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

                default:
                    System.out.println("please enter the correct choice");
                    break;
            }
        }while(ch>=1&&ch<=6);
    }

    public static void main(String[] args) {
        new Dictionary().check();

    }


}


