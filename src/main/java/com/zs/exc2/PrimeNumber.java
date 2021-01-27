package main.java.com.zs.exc2;

import java.util.*;

/**
 * This class is developed to print the prime number from 1 to a given number.
 */

public class PrimeNumber {
    int n, flag = 0;
    Scanner scan = new Scanner(System.in);

    //This function is printing prime numbers
    public void printPrime() {
        System.out.println("Enter a number");
        n = scan.nextInt();
        for (int i = 2; i < n; i++) {
            flag = 0;
            for (int j = 2; j < Math.sqrt(i); j++) {

                if (i % j == 0) {
                    flag = 1;
                    break;

                }
            }
            if (flag == 0)
                System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new PrimeNumber().printPrime();
    }
}
