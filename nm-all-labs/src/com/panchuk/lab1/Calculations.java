package com.panchuk.lab1;

import java.util.Scanner;

import static java.lang.Math.pow;

public class Calculations {
    private static final int NUMBER_OF_VARIANT = 18;

    public static Expression inputData(int check) {
        Scanner scan = new Scanner(System.in);
        System.out.print("x1 = ");
        double x1 = scan.nextDouble();
        System.out.print("x2 = ");
        double x2 = scan.nextDouble();
        System.out.print("x3 = ");
        double x3 = scan.nextDouble();

        return new Expression(x1, x2, x3, check);
    }


    /**
     * defines relative error of argument
     * @return relative error of argument
     */
    public static double relErrX(double x, int precision) {
        int digit = Calculations.defDigit(x);

        if (precision == 1) return 1.0 / digit;
        else return (1.0 / (2 * digit)) * pow((0.1), (precision - 1));
    }

    /**
     * defines precision
     * @return n - amount of exact digits
     */
    public static int getPrecision(double x, int variant) {
        if (variant == 1) {
            return (x + "").length() - 1;
        } else {
            double error = (NUMBER_OF_VARIANT * 0.001) / x;
            int m = Calculations.calculatePositionDigit(x);
            int n = 0;
            for (int i = 0; i < 20; i++) {
                double rez = pow(10, (m - n + 1));
                if (error <= rez * 0.5) n++;
                else break;
            }
            return n;
        }
    }

    /**
     * defines the first significant digit
     */
    public static int defDigit(double number) {
        double x = number;
        if (x < 10 && x >= 1) return (int) x % 10;
        else if (x < 1) {
            for (int i = 0; i < 32; i++) {
                if (x > 1) return (int)x;
                x *= 10;
            }
        }
        else {
            for (int i = 0; i < 32; i++) {
                if (x < 10) return (int)x;
                x /= 10;
            }
        }
        return 0;
    }

    /**
     * defines position of the first significant digit
     * @return m - power of the first exact number
     */
    public static int calculatePositionDigit(double x) {
        int precision = 0;
        if (x < 1) {
            for (int i = 0; i < 32; i++) {
                x *= 10;
                precision--;
                if (x >= 1) return precision;
            }
        } else if (x >= 10) {
            for (int i = 0; i < 32; i++) {
                x /= 10;
                precision++;
                if (x < 10) return precision;
            }
        }
        return 0;
    }
}
