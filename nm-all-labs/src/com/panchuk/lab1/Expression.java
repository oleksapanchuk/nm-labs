package com.panchuk.lab1;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.exp;

public class Expression {
    private static final int NUMBER_OF_VARIANT = 18;
    private final double x1;
    private final double x2;
    private final double x3;
    private int n1;
    private int n2;
    private int n3;
    private double absoluteError;
    private double relativeError;
    private double func;

    public Expression(double x1, double x2, double x3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.n1 = getPrecision(x1);
        this.n2 = getPrecision(x2);
        this.n3 = getPrecision(x3);
        this.func = calculateFunc();
        this.absoluteError = calculateAbsoluteErrorForB();
        this.relativeError = calculateRelativeError();
    }

    public double calculateFunc() {
        return (7 * x1 * x1) + (5 * x2 * x2) + (6 * x3 * x3) + (6 * x1 * x3) - (4 * x2) - (5 * exp(x2 * x3));
    }

    public double calculateAbsoluteError() {
        double funcPartialDerivativeX1 = (14 * this.x1) + (6 * this.x3);
        double funcPartialDerivativeX2 = (10 * this.x2) - (5 * this.x3 * exp(this.x2 * this.x3)) - 4;
        double funcPartialDerivativeX3 = (12 * this.x3) + (6 * this.x1) - (5 * this.x2 * exp(this.x2 * this.x3));
        System.out.println("Partial derivatives of:\n\tX1) 14 * x1 + 6 * x3 = " + funcPartialDerivativeX1 +
                "\n\tX2) 10 * x2 - 5 * x3 * exp(x2 * x3) - 4 = " + funcPartialDerivativeX2 +
                "\n\tX3) 12 * x3 + 6 * x1 - 5 * x2 * exp(x2 * x3 = " + funcPartialDerivativeX3 + "\n");

        double relativeMarginErrorX1 = relativeMarginErrorX(this.x1, this.n1);
        double relativeMarginErrorX2 = relativeMarginErrorX(this.x2, this.n2);
        double relativeMarginErrorX3 = relativeMarginErrorX(this.x3, this.n3);
        System.out.println("Relative error of:\n\tX1 = " + String.format("%.9f", relativeMarginErrorX1) +
                "  X2 = " + String.format("%.9f", relativeMarginErrorX2) +
                "  X3 = " + String.format("%.9f", relativeMarginErrorX3));

        double absoluteMarginErrorX1 = relativeMarginErrorX(this.x1, this.n1) * abs(this.x1);
        double absoluteMarginErrorX2 = relativeMarginErrorX(this.x2, this.n2) * abs(this.x2);
        double absoluteMarginErrorX3 = relativeMarginErrorX(this.x3, this.n3) * abs(this.x3);
        System.out.println("Absolute error of:\n\tX1 = " + String.format("%.9f", absoluteMarginErrorX1) +
                "  X2 = " + String.format("%.9f", absoluteMarginErrorX2) +
                "  X3 = " + String.format("%.9f", absoluteMarginErrorX3));

        return (abs(funcPartialDerivativeX1) * absoluteMarginErrorX1) +
                (abs(funcPartialDerivativeX2) * absoluteMarginErrorX2) +
                (abs(funcPartialDerivativeX3) * absoluteMarginErrorX3);
    }

    public double calculateAbsoluteErrorForB() {
        double funcPartialDerivativeX1 = (14 * this.x1) + (6 * this.x3);
        double funcPartialDerivativeX2 = (10 * this.x2) - (5 * this.x3 * exp(this.x2 * this.x3)) - 4;
        double funcPartialDerivativeX3 = (12 * this.x3) + (6 * this.x1) - (5 * this.x2 * exp(this.x2 * this.x3));

        double absoluteMarginErrorX1X2X3 = NUMBER_OF_VARIANT * 0.001;

        double relativeMarginErrorX1 = absoluteMarginErrorX1X2X3 / this.x1;
        double relativeMarginErrorX2 = absoluteMarginErrorX1X2X3 / this.x2;
        double relativeMarginErrorX3 = absoluteMarginErrorX1X2X3 / this.x3;

        System.out.println("Partial derivatives of:\n\tX1) 14 * x1 + 6 * x3 = " + funcPartialDerivativeX1 +
                "\n\tX2) 10 * x2 - 5 * x3 * exp(x2 * x3) - 4 = " + funcPartialDerivativeX2 +
                "\n\tX3) 12 * x3 + 6 * x1 - 5 * x2 * exp(x2 * x3 = " + funcPartialDerivativeX3 + "\n");

        System.out.println("Relative error of:\n\tX1 = " + String.format("%.9f", relativeMarginErrorX1) +
                            "  X2 = " + String.format("%.9f", relativeMarginErrorX2) +
                            "  X3 = " + String.format("%.9f", relativeMarginErrorX3));

        System.out.println("\nAbsolute error X1, X2 and X3 = " + String.format("%.3f", absoluteMarginErrorX1X2X3));

        return absoluteMarginErrorX1X2X3 * (abs(funcPartialDerivativeX1) +
                                            abs(funcPartialDerivativeX2) +
                                            abs(funcPartialDerivativeX3));
    }


    public double calculateRelativeError() {
        return absoluteError / func;
    }

    public int getPrecision(double x) {
        double error = (NUMBER_OF_VARIANT * 0.001) / x;
        int m = calculatePositionDigit(x);
        int n = 0;
        for (int i = 0; i < 20; i++) {
            double rez = pow(10, (m - n + 1));
            if (error <= rez * 0.5) n++;
            else break;
        }
        return n;
    }

    private int calculatePositionDigit(double x) {
        int precision = 0;
        if (x < 1) {
            for (int i = 0; i < 32; i++) {
                x *= 10;
                precision--;
                if (x >= 1) return precision;
            }
        }
        else if (x >= 10){
            for (int i = 0; i < 32; i++) {
                x /= 10;
                precision++;
                if (x < 10) return precision;
            }
        }
        return 0;
    }

    public double relativeMarginErrorX(double x, int precision) {
        double tempX = x;
        int digit = getDigit(tempX);
        if (digit == 0) throw new RuntimeException("Division by zero");
        double relativeMarginErrorX;
        if (precision == 1) relativeMarginErrorX = (1.0 / digit);
        else relativeMarginErrorX = (1.0 / (2 * digit)) * pow((0.1), (precision - 1));

        return relativeMarginErrorX;
    }

    private int getDigit(double x) {
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

    @Override
    public String toString() {
        return "\nData:" +
                "\n\tn1 = " + n1 + "\tx1 = " + x1 +
                "\n\tn2 = " + n2 + "\tx2 = " + x2 +
                "\n\tn3 = " + n3 + "\tx3 = " + x3 +
                "\nResult:\n\tAbsolute error of func = " + this.absoluteError +
                "\n\tRelative error of func = " + this.relativeError +
                "\n\nEnd of programme!";
    }
}
