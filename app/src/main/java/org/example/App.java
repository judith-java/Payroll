package org.example;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PayrollCalculator calculator = new PayrollCalculator();

    System.out.println("Welcome to the Payroll Program!\n");

    // Custom Pay Rate
    double payRate;
    do {
      System.out.print("Enter your hourly pay rate: ");
      payRate = scanner.nextDouble();
      if (payRate < 0) {
        System.out.println("Pay rate cannot be negative. Please try again.");
      }
    } while (payRate < 0);

    System.out.print("How many hours did you work this week? ");
    double hoursWorked = scanner.nextDouble();

    System.out.print("How many children do you have? ");
    int dependents = scanner.nextInt();
    if (dependents < 0) {
      System.out.println("Negative number of children detected. Setting dependents to 0.");
      dependents = 0;
    }

    // Life Insurance Plan
    int insuranceChoice;
    double lifeInsuranceCost = 0;
    while (true) {
      System.out.println("Which life insurance plan do you want to select?\n");
      System.out.println("  (1) no plan");
      System.out.println("  (2) single plan");
      System.out.println("  (3) married plan");
      System.out.println("  (4) married with children plan\n");

      insuranceChoice = scanner.nextInt();
      if (insuranceChoice == 1) break;
      else if (insuranceChoice == 2) {
        lifeInsuranceCost = 5.00;
        break;
      } else if (insuranceChoice == 3) {
        lifeInsuranceCost = 10.00;
        break;
      } else if (insuranceChoice == 4) {
        if (dependents > 0) {
          lifeInsuranceCost = 15.00;
          break;
        } else {
          System.out.println("\nSorry! You need at least one child to select that plan.\n");
        }
      } else {
        System.out.println("\nInvalid option. Please choose a valid life insurance plan.\n");
      }
    }

    double grossPay = calculator.calculateGrossPay(hoursWorked, payRate);
    double socSec = calculator.calculateSocialSecurityTax(grossPay);
    double fedTax = calculator.calculateFederalIncomeTax(grossPay);
    double stTax = calculator.calculateStateIncomeTax(grossPay);

    double afterTaxPay = grossPay - (socSec + fedTax + stTax);
    double unionDues = 10.00;
    double healthIns = calculator.calculateInsurance(dependents);

    double netPay;
    boolean hasDebt = false;

    // Handle negative pay after taxes
    if (afterTaxPay < unionDues + healthIns + lifeInsuranceCost) {
      netPay = afterTaxPay;
      hasDebt = true;
    } else {
      netPay = afterTaxPay - unionDues - healthIns - lifeInsuranceCost;
    }

    System.out.println("\nPayroll Stub:\n");
    System.out.printf("   Hours:   %.1f%n", hoursWorked);
    System.out.printf("    Rate:   %.2f $/hr%n", payRate);
    System.out.printf("   Gross:   $%7.2f%n%n", grossPay);

    System.out.printf("  SocSec:   $%7.2f%n", socSec);
    System.out.printf("  FedTax:   $%7.2f%n", fedTax);
    System.out.printf("   StTax:   $%7.2f%n", stTax);

    if (!hasDebt) {
      System.out.printf("   Union:   $%7.2f%n", unionDues);
      System.out.printf("     Ins:   $%7.2f%n", healthIns);
      if (lifeInsuranceCost > 0)
        System.out.printf(" LifeIns:   $%7.2f%n", lifeInsuranceCost);
    }

    System.out.printf("%n     Net:   $%7.2f%n%n", netPay);

    if (hasDebt) {
      System.out.println("The employee still owes:\n");
      System.out.printf("   Union:   $%7.2f%n", unionDues);
      System.out.printf("     Ins:   $%7.2f%n", healthIns);
      if (lifeInsuranceCost > 0)
        System.out.printf(" LifeIns:   $%7.2f%n", lifeInsuranceCost);
    }

    System.out.println("\nThank you for using the Payroll Program!");
    scanner.close();
  }
}
