package com.zs.exc3;
import java.io.*;
import java.util.*;
public class Dictionary
{
       Scanner scan=new Scanner(System.in);
        String inputstring=new String();
        String meaning=new String();
        int ch;
        char initial;
        String line="";
        TreeMap<String, String> ham = new TreeMap<String,String>();
        //BufferedReader br = new BufferedReader(new FileReader(file1));
        public void insert()
        {
            System.out.println("Enter a string");
            inputstring = scan.next();
            System.out.println("Enter meaning");
            meaning = scan.next();
            if (ham.containsKey(inputstring))
                System.out.println("string is present");
            else
            {
                ham.put(inputstring, meaning);
            }
        }
        public void search()
        {
            System.out.println("Enter a string searchstring");
            inputstring = scan.next();
            if (ham.containsKey(inputstring))
                System.out.println("string is present");
            else
                System.out.println("This word is not present");

        }
        public void meaning()
        {
            System.out.println("Enter a string");
            inputstring = scan.next();
            if(ham.isEmpty())
            {
                System.out.println("Dictionary is empty");

            }
            else
            {
                if(ham.containsKey(inputstring))
                    System.out.println(ham.get(inputstring));
                else
                    System.out.println("This Word is not present in dictionary");
            }

        }
        public void display()
        {
            Iterator hmIterator = ham.entrySet().iterator();
            System.out.println("dictionary");
            if(ham.isEmpty())
            {
                System.out.println("Dictionary is empty");

            }
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();

                System.out.println(mapElement.getKey() + " : " +mapElement.getValue());
            }
        }
        public void startingwith()
        {
            System.out.println("Enter a string from where to start search");
            inputstring = scan.next();
            Map<String,String> mp=ham.tailMap(inputstring);
            Set<String> arl=mp.keySet();
            for(String str:arl)
            {
                if(str.startsWith(inputstring))
                    System.out.println(str);
            }
        }
        public void similar()
        {
            System.out.println("Enter a word whose similar is to be display");
            inputstring = scan.next();
            int l=inputstring.length();
            int difcounter;
            Set<String> arl2=ham.keySet();


            for(String str:arl2)
            {
                difcounter=0;

                for(int j=0;j<str.length();j++)
                {
                    if(str.charAt(j)!=inputstring.charAt(j))
                        difcounter++;
                    if(difcounter>1)
                        continue;;
                }
                if(difcounter<=1)
                    System.out.println(str);


            }

        }
        public  void check()
        {
            while (true)
            {

                System.out.println("1.insert 2.Search 3.Meaning 4.statingwith 5.Similar 6.Display 7.Exit");
                System.out.println("enter your cho1ice");
                ch = scan.nextInt();
                switch (ch)
                {
                    case 1: insert();
                        break;
                    case 2: search();
                        break;
                    case 3: meaning();
                        break;
                    case 4: startingwith();
                        break;
                    case 5:similar();
                        break;
                    case 6:display();
                        break;
                    case 7:System.exit(0);
                    default:System.out.println("please enter the correct choice");
                }
            }
        }
        public static  void main(String[] args)
        {
            new Dictionary().check();

        }


    }


