package com.zs.exc2;
import java.util.Scanner;

public class Printdifferent {


        public static void main(String[] args) {

            String inputstring;

            char ch;

            Scanner SC=new Scanner(System.in);

            System.out.println("enter your string");
            inputstring= SC.nextLine();
            inputstring=inputstring.toUpperCase();
            for ( ch = 'A'; ch <= 'Z'; ch++ )
            {
                for ( int i = 0; i < inputstring.length(); i++ ) {
                    if ( ch == inputstring.charAt(i) ) {
                        System.out.print(ch + " ");
                        break;
                    }
                }
            }

        }

    }
