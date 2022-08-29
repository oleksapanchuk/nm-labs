package com.panchuk.lab1;

import java.util.Scanner;

import static com.panchuk.lab1.DefineError.inputData;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nDeveloper - Panchuk Oleksandr - KN-203 - Variant - 18 (10)");

        System.out.print(new StringBuilder().append("\nChoose a program option: \n\t")
                .append("print '1' - exact numbers are given\n\t")
                .append("print '2' - an error is given\n\n")
                .append("Your choice: "));
        Scanner scanner = new Scanner(System.in);
        int check = scanner.nextInt();

        Expression expression = inputData(check);
        System.out.println(expression);
    }
}
