package com.ZS.exc1;

import java.util.Scanner;
import java.util.Stack;
class Bodmas
{
    String inpstring="";
    Scanner scan=new Scanner(System.in);
    public void inputstring()
        {
          inpstring= scan.nextLine();
        }
        public int calculate()
        {
            char[] num = inpstring.toCharArray();
            Stack<Integer> operands = new Stack<Integer>();
            Stack<Character> opt = new Stack<Character>();
            for (int i = 0; i < num.length; i++)
            {
                System.out.println(num[i]);
                if (num[i] == ' ')
                    continue;
                if (Character.isDigit(num[i]))
                {
                    StringBuffer stbf = new StringBuffer();
                    while (i < num.length&&Character.isDigit(num[i]))
                        stbf.append(num[i++]);
                    operands.push(Integer.parseInt(stbf.toString()));
                    i=i-1;
                }
                else if (num[i] == '(')
                    opt.push(num[i]);
                else if (num[i] == ')')
                {
                    while (opt.peek() != '(')
                        operands.push(aithmeticoperation(opt.pop(), operands.pop(), operands.pop()));
                    opt.pop();

                }
                else if (num[i] == '+' || num[i] == '-' || num[i] == '*' || num[i] == '/')
                {
                    while (!opt.isEmpty() && Prec(num[i]) <= Prec(opt.peek()))
                        operands.push(aithmeticoperation(opt.pop(), operands.pop(), operands.pop()));


                    opt.push(num[i]);
                }
                System.out.println(operands);
                System.out.println(opt);
            }
            while (!opt.empty())
                operands.push(aithmeticoperation(opt.pop(), operands.pop(), operands.pop()));


            return operands.pop();
        }

            public static int aithmeticoperation(char op, int b, int a)
       {
            switch (op)
           {
              case '+':
                   return a + b;
              case '-':
                   return a - b;
              case '*':
                   return a * b;
              case '/':
                   if (b == 0)
                      throw new
                            UnsupportedOperationException("Cannot divide by zero");
                      return a / b;
           }
           return 0;
       }

    static int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        Bodmas obj=new Bodmas();
        obj.inputstring();
        System.out.println((obj.calculate()));

    }
}