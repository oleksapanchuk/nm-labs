package com.panchuk.lab1;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.exp;

public class Expression {
    private static final int NUMBER_OF_VARIANT = 18;
    private final double x1;
    private final double x2;
    private final double x3;
    private final int variant;
    private int n1;
    private int n2;
    private int n3;
    private int fed1;
    private int fed2;
    private int fed3;
    private double absoluteError;
    private double relativeError;
    private double funcRes;

    public Expression(double x1, double x2, double x3, int variant) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.variant = variant;
    }

    /**
     * invoke and set all necessary value
     */
    public void setValue() {

        this.n1 = Calculations.getPrecision(x1, variant);
        this.n2 = Calculations.getPrecision(x2, variant);
        this.n3 = Calculations.getPrecision(x3, variant);

        this.fed1 = Calculations.defDigit(x1);
        this.fed2 = Calculations.defDigit(x2);
        this.fed3 = Calculations.defDigit(x3);

        this.funcRes = calculateFunc();
        this.absoluteError = calculateAbsoluteError(variant);
        this.relativeError = calculateRelativeError();
    }

    /**
     * calculate absolute error of func
     */
    private double calculateAbsoluteError(int variant) {
        double fPartDerX1 = (14 * this.x1) + (6 * this.x3);
        double fPartDerX2 = (10 * this.x2) - (5 * this.x3 * exp(this.x2 * this.x3)) - 4;
        double fPartDerX3 = (12 * this.x3) + (6 * this.x1) - (5 * this.x2 * exp(this.x2 * this.x3));
        System.out.println("Partial derivatives of:\n\tX1) 14 * x1 + 6 * x3 = " + fPartDerX1 +
                "\n\tX2) 10 * x2 - 5 * x3 * exp(x2 * x3) - 4 = " + fPartDerX2 +
                "\n\tX3) 12 * x3 + 6 * x1 - 5 * x2 * exp(x2 * x3 = " + fPartDerX3 + "\n");

        if (variant == 1) {

            double relErrX1 = Calculations.relErrX(this.x1, this.n1);
            double relErrX2 = Calculations.relErrX(this.x2, this.n2);
            double relErrX3 = Calculations.relErrX(this.x3, this.n3);

            System.out.println("Relative error of:\n\tX1 = " + String.format("%.9f", relErrX1) +
                    "  X2 = " + String.format("%.9f", relErrX2) +
                    "  X3 = " + String.format("%.9f", relErrX3));

            double absolErrX1 = Calculations.relErrX(this.x1, this.n1) * abs(this.x1);
            double absolErrX2 = Calculations.relErrX(this.x2, this.n2) * abs(this.x2);
            double absolErrX3 = Calculations.relErrX(this.x3, this.n3) * abs(this.x3);
            System.out.println("Absolute error of:\n\tX1 = " + String.format("%.9f", absolErrX1) +
                    "  X2 = " + String.format("%.9f", absolErrX2) +
                    "  X3 = " + String.format("%.9f", absolErrX3));

            return (abs(fPartDerX1) * absolErrX1) +
                    (abs(fPartDerX2) * absolErrX2) +
                    (abs(fPartDerX3) * absolErrX3);
        } else {

            double absolErrX123 = NUMBER_OF_VARIANT * 0.001;

            double relErrX1 = absolErrX123 / this.x1;
            double relErrX2 = absolErrX123 / this.x2;
            double relErrX3 = absolErrX123 / this.x3;

            System.out.println("Relative error of:\n\tX1 = " + String.format("%.9f", relErrX1) +
                    "  X2 = " + String.format("%.9f", relErrX2) +
                    "  X3 = " + String.format("%.9f", relErrX3));

            System.out.println("\nAbsolute error X1, X2 and X3 = " + String.format("%.3f", absolErrX123));

            return absolErrX123 * (abs(fPartDerX1) +
                    abs(fPartDerX2) +
                    abs(fPartDerX3));
        }
    }

    /**
     * calculate relative error of func
     */
    private double calculateRelativeError() {
        return absoluteError / funcRes;
    }

    /**
     * calculate value of func
     */
    private double calculateFunc() {
        return (7 * x1 * x1) + (5 * x2 * x2) + (6 * x3 * x3) + (6 * x1 * x3) - (4 * x2) - (5 * exp(x2 * x3));
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\n\tData:").append("\nx1 = ").append(x1)
                .append("\nn = ").append(n1).append("\nfirst exactly digit = ")
                .append(fed1).append("\nx2 = ").append(x2)
                .append("\nn = ").append(n2).append("\nfirst exactly digit = ")
                .append(fed2).append("\nx3 = ").append(x3)
                .append("\nn = ").append(n3).append("\nfirst exactly digit = ")
                .append(fed3).append("\n\tResult:").append("\nAbsolute error of func = ")
                .append(absoluteError).append("\nRelative error of func = ")
                .append(relativeError).toString();
    }
}
