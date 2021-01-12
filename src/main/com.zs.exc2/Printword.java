package com.zs.exc2;
import java.util.Scanner;
public class Printword {
    Scanner scan=new Scanner(System.in);
    String inputstring="";
    public void display()
    {
        System.out.print("enter a string");
        inputstring=scan.nextLine();
        String[] stresult=inputstring.split(" ");


        for(int i=0;i< stresult.length;i++)
            System.out.println(stresult[i]);
    }
    public static void main(String args[])
    {
        Printword obj=new Printword();
        obj.display();
    }
}
