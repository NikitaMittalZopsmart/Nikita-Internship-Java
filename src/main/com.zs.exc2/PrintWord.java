package com.zs.exc2;
import java.util.Scanner;

public class PrintWord {
    Scanner scan=new Scanner(System.in);
    String inputString ="";
    //This function is displaying different word.
    public void display()
    {
        System.out.print("enter a string");
        inputString =scan.nextLine();
        String[] stresult= inputString.split(" ");


        for(int i=0;i< stresult.length;i++)
            System.out.println(stresult[i]);
    }
    public static void main(String args[])
    {

        new PrintWord().display();
    }
}
