package java.com.zs.exc1;

import java.util.Scanner;
import java.util.Stack;

/**
 *This class is to calculate the BODMAS value for an arithemetic expression.

 */
class Bodmas {
    String inputString = "";
    Scanner scan = new Scanner(System.in);

    //Taking an input expression from user.
    public void inputingString() {
        inputString = scan.nextLine();
        System.out.println((calculate()));
    }//Function is ending



    /**
     * This method is calculating the BODMAS value for input string.
     * @return the resulted value after applying the BODMAS.
     */
    public int calculate() {
        char[] num = inputString.toCharArray();
        Stack<Integer> operands = new Stack<Integer>(); //Storing numerical value in this stack
        Stack<Character> operators = new Stack<Character>();//Storing operators in this stack
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
            if (num[i] == ' ')
                continue;
            if (Character.isDigit(num[i])) {
                StringBuilder stbf = new StringBuilder();
                while (i < num.length && Character.isDigit(num[i]))
                    stbf.append(num[i++]);
                operands.push(Integer.parseInt(stbf.toString()));
                i = i - 1;
            } else if (num[i] == '(')
                operators.push(num[i]);
            else if (num[i] == ')') {
                while (operators.peek() != '(')
                    operands.push(aithmeticOperation(operators.pop(), operands.pop(), operands.pop()));
                operators.pop();

            } else if (num[i] == '+' || num[i] == '-' || num[i] == '*' || num[i] == '/') {
                while (!operators.isEmpty() && Prec(num[i]) <= Prec(operators.peek()))
                    operands.push(aithmeticOperation(operators.pop(), operands.pop(), operands.pop()));


                operators.push(num[i]);
            }

        }
        while (!operators.empty())
            operands.push(aithmeticOperation(operators.pop(), operands.pop(), operands.pop()));


        return operands.pop();
    }

    /**
     * This function is performing airthmetic operation.
     * @param op op is the operator.
     * @param b b is 1st operand.
     * @param a a is 2nd operand.
     * @return the output value.
     */
    //airthmeticOperation function is performing is airthmetic operation.
    public static int aithmeticOperation(char op, int b, int a) {
        switch (op) {
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
            default:
                System.out.println("Invalid operator");
        }
        return 0;
    }

    /**
     * This function is returning the precedence of the operators.
     * @param ch ch is a operator.
     * @return The precedence of the given operators.
     */

    static int Prec(char ch) {
        switch (ch) {
            case '+':
                return 1;
            case '-':
                return 2;

            case '*':
                return 3;
            case '/':
                return 4;


            default:
                System.out.println("Invalid operator");
        }
        return -1;
    }


    public static void main(String[] args) {
        new Bodmas().inputingString();


    }
}