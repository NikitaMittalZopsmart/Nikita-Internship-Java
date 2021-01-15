package com.zs.exc2;

import java.util.Scanner;

public class EmiCal {
    float principle, rate, time;
    Scanner scan = new Scanner(System.in);
    //Taking inputs from user
    public void inputArguments() {
        System.out.println("Enter principle amount");
        principle = scan.nextFloat();
        System.out.println("Enter rate of interest");
        rate = scan.nextFloat();
        System.out.println("Enter repayment period");
        time = scan.nextFloat();
        calculate();
    }
    //Calculating EMI for the given inputs.
    void calculate() {
        float emi;


        emi = (float) ((principle * rate * Math.pow(1 + rate, time)) / (Math.pow(1 + rate, time) - 1));
        System.out.println("The calculated EMI is" + emi);

    }

    public static void main(String[] args) {

        new EmiCal().inputArguments();

    }
}


