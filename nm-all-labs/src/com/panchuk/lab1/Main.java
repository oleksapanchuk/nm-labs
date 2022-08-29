package com.panchuk.lab1;

import static com.panchuk.lab1.Calculations.inputData;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nDeveloper - Panchuk Oleksandr - KN-203 - Variant - 18 (10 in subgroup)");

        Expression expression = inputData();
        expression.setValue();
        System.out.println(expression);
    }
}

/*
        Expression expression1 = new Expression(0.513, 0.62, 1.68, 1);
        expression1.setValue();
        System.out.println(expression1);
        Expression expression2 = new Expression(0.513, 0.62, 1.68, 2);
        expression2.setValue();
        System.out.println(expression2);
 */
