package org.example;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PayrollCalculator calculator = new PayrollCalculator();

    System.out.println("Welcome to the Payroll Program!\n");

    System.out.print("How many hours did you work this week? ");
    double hoursWorked = scanner.nextDouble();

    System.out.print("How many children do you have? ");
    int dependents = scanner.nextInt();

    double grossPay = calculator.calculateGrossPay(hoursWorked);
    double socSec = calculator.calculateSocialSecurityTax(grossPay);
    double fedTax = calculator.calculateFederalIncomeTax(grossPay);
    double stTax = calculator.calculateStateIncomeTax(grossPay);
    double unionDues = calculator.calculateUnionDues();
    double insurance = calculator.calculateInsurance(dependents);
    double netPay = calculator.calculateNetPay(grossPay, dependents);

    System.out.println("\nPayroll Stub:\n");

    // Format output nicely, align dollar amounts
    System.out.printf("   Hours:   %.1f%n", hoursWorked);
    System.out.printf("    Rate:   %.2f $/hr%n", PayrollCalculator.HOURLY_RATE);
    System.out.printf("   Gross:   $%7.2f%n%n", grossPay);

    System.out.printf("  SocSec:   $%7.2f%n", socSec);
    System.out.printf("  FedTax:   $%7.2f%n", fedTax);
    System.out.printf("   StTax:   $%7.2f%n", stTax);
    System.out.printf("   Union:   $%7.2f%n", unionDues);
    System.out.printf("     Ins:   $%7.2f%n%n", insurance);

    System.out.printf("     Net:   $%7.2f%n%n", netPay);

    System.out.println("Thank you for using the Payroll Program!");

    scanner.close();
  }
}
