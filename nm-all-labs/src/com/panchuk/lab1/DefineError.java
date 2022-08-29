package com.panchuk.lab1;

import java.util.Scanner;

public class DefineError {

    public static Expression inputData(int check) {
        Scanner scan = new Scanner(System.in);
        System.out.print("x1 = ");
        double x1 = scan.nextDouble();
        System.out.print("x2 = ");
        double x2 = scan.nextDouble();
        System.out.print("x3 = ");
        double x3 = scan.nextDouble();

        if (check == 1) {
            System.out.print("n1 = ");
            int n1 = scan.nextInt();
            System.out.print("n2 = ");
            int n2 = scan.nextInt();
            System.out.print("n3 = ");
            int n3 = scan.nextInt();
            return new Expression(x1, x2, x3, n1, n2, n3);
        } else if (check == 2) {
            return new Expression(x1, x2, x3);
        } else
            throw new IllegalArgumentException();
    }
}
