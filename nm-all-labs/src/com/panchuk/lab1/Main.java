package com.panchuk.lab1;

import java.util.Scanner;

import static com.panchuk.lab1.Calculations.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nDeveloper - Panchuk Oleksandr - KN-203 - Variant - 18 (10 in subgroup)");

        System.out.print(new StringBuilder().append("\nChoose a program option: \n\t")
                .append("print '1' - exact numbers are given\n\t")
                .append("print '2' - an error is given\n\n")
                .append("Your choice: "));
//        Scanner scanner = new Scanner(System.in);
//        int check = scanner.nextInt();
//
//        Expression expression = inputData(check);
        Expression expression1 = new Expression(0.513, 0.62, 1.68, 1);
        expression1.setValue();
        System.out.println(expression1);
        Expression expression2 = new Expression(0.513, 0.62, 1.68, 2);
        expression2.setValue();
        System.out.println(expression2);

    }
}
