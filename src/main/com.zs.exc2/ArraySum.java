package com.zs.exc2;

public class ArraySum {
    //This function is adding 1D array.
    public void sum1Darrays() {
        int a[] = {1, 2, 3, 4, 5};
        int b[] = {6, 7, 8, 9, 10};
        int c[] = new int[5];

        for (int i = 0; i < 5; ++i) {

            c[i] = a[i] + b[i];
        }
        System.out.println("sum of 1D arrays is");
        for (int i = 0; i < 5; i++) {
            System.out.println(c[i]);
        }
    }

    //This function is adding 2D array
    public void sum2Darrays() {

        int a[][] = {{1, 2, 3}, {4, 5, 6}};
        int b[][] = {{7, 8, 9}, {10, 11, 12}};


        int c[][] = new int[2][3];

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {

                c[i][j] = a[i][j] + b[i][j];

            }
        }
        System.out.println("sum of 2D arrays is");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(c[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        new ArraySum().sum1Darrays();
        new ArraySum().sum2Darrays();
    }
}
