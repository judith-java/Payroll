package org.example;

public class PayrollCalculator {
    public static final double OVERTIME_MULTIPLIER = 1.5;
    public static final int REGULAR_HOURS = 40;

    public double calculateGrossPay(double hoursWorked, double hourlyRate) {
        if (hoursWorked <= REGULAR_HOURS) {
            return hoursWorked * hourlyRate;
        } else {
            double overtimeHours = hoursWorked - REGULAR_HOURS;
            return (REGULAR_HOURS * hourlyRate) + (overtimeHours * hourlyRate * OVERTIME_MULTIPLIER);
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

    public double calculateInsurance(int dependents) {
        return (dependents >= 3) ? 35.00 : 15.00;
    }
}
