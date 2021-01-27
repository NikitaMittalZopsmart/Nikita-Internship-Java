package main.java.com.zs.exc2;

import java.util.Scanner;

/**
 * This class is developed to print different letters in a given string.
 */
public class PrintDifferent {

   //Printing different letters from a given string.
    public static void main(String[] args) {

        String inputString;

        char ch;

        Scanner SC = new Scanner(System.in);

        System.out.println("enter your string");
        inputString = SC.nextLine();
        inputString = inputString.toUpperCase();
        for (ch = 'A'; ch <= 'Z'; ch++) {
            for (int i = 0; i < inputString.length(); i++) {
                if (ch == inputString.charAt(i)) {
                    System.out.print(ch + " ");
                    break;
                }
            }
        }

    }

}
