package main.java.com.zs.exc2;

import java.util.Scanner;

/**
 * This class is developed to convert a decimal number into a binary number.
 */
class DecimalToBinary {
    int number, rem;
    String result = "";
    String binResult = "";
    Scanner scan = new Scanner(System.in);
    //This fuction is converting decimal number into binary number.

    /**
     * This function is converting a decimal number into a binary number.
     */
    public void convert() {
        System.out.println("Enter a number");
        number = scan.nextInt();
        while (number != 1) {
            rem = number % 2;
            result = result + Integer.toString(rem);
            number = number / 2;
            if (number == 1)
                break;
        }

        result = result + Integer.toString(number);
        System.out.println(result);
        int l = result.length() - 1;
        for (int i = l; i >= 0; i--) {
            System.out.println(result.charAt(i));
            binResult = binResult + result.charAt(i);
        }
        System.out.println(binResult);
        System.out.println("Binary conversion of this decimal is" + binResult);

    }

    public static void main(String[] args) {

        new DecimalToBinary().convert();
    }

}