package com.zs.exc2;

import java.util.Scanner;
class Decimaltobinary
{
    int number,rem;
    String result="";
    String binresult="";
    Scanner scan=new Scanner(System.in);
    public void convert()
        {
            System.out.println("Enter a number");
           number= scan.nextInt();
           while(number!=1)
           {
              rem=number%2;
              result=result+Integer.toString(rem);
              number=number/2;
              if(number==1)
                  break;
           }

           result=result+Integer.toString(number);
           System.out.println(result);
           int l=result.length()-1;
           for(int i=l;i>=0;i--) {
               System.out.println(result.charAt(i));
               binresult=binresult+result.charAt(i);
           }
           System.out.println(binresult);
           System.out.println("Binary conversion of this decimal is"+binresult);

        }
        public static void main(String[] args)
        {
         Decimaltobinary obj=new Decimaltobinary();
         obj.convert();
        }

}