package com.zs.exc2;
import java.util.Scanner;
public class Arraysum {

    public void sum1darrays()
    {
        int a[] = {1,2,3,4,5};
        int b[] = {6,7,8,9,10};
        int c[] = new int[5];

        for(int i=0; i<5; ++i) {

            c[i] = a[i] + b[i];
        }
        System.out.println("sum of 1D arrays is");
        for(int i=0;i<5;i++)
        {
            System.out.println(c[i]);
        }
    }
    public void sum2darrays() {

        int a[][] = {{1, 2, 3}, {4, 5, 6}};
        int b[][] = {{7, 8, 9}, {10, 11, 12}};


        int c[][] = new int[2][3];

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {

                c[i][j] = a[i][j] + b[i][j];

            }
        }
            System.out.println("sum of 2D arrays is");
            for(int i=0;i<2;i++)
            {
                for(int j=0;j<3;j++)
                System.out.print(c[i][j]);
                System.out.println();
            }
        }

        public static void main(String[] args)
        {
            Arraysum obj=new Arraysum();
            obj.sum1darrays();
            obj.sum2darrays();
        }
}
