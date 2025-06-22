package org.example;

public class PayrollCalculator {
    public static final double HOURLY_RATE = 16.78;
    public static final double OVERTIME_MULTIPLIER = 1.5;
    public static final int REGULAR_HOURS = 40;

    public double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= REGULAR_HOURS) {
            return hoursWorked * HOURLY_RATE;
        } else {
            double overtimeHours = hoursWorked - REGULAR_HOURS;
            return (REGULAR_HOURS * HOURLY_RATE) + (overtimeHours * HOURLY_RATE * OVERTIME_MULTIPLIER);
        }
    }

    public double calculateSocialSecurityTax(double grossPay) {
        return grossPay * 0.06;
    }

    public double calculateFederalIncomeTax(double grossPay) {
        return grossPay * 0.14;
    }

    public double calculateStateIncomeTax(double grossPay) {
        return grossPay * 0.05;
    }

    public double calculateUnionDues() {
        return 10.00;
    }

    public double calculateInsurance(int dependents) {
        return (dependents >= 3) ? 35.00 : 15.00;
    }

    public double calculateNetPay(double grossPay, int dependents) {
        double expenses = calculateSocialSecurityTax(grossPay) + calculateFederalIncomeTax(grossPay)
                          + calculateStateIncomeTax(grossPay) + calculateUnionDues() + calculateInsurance(dependents);
        return grossPay - expenses;
    }
}
