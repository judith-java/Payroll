package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest{
  PayrollCalculator calculator = new PayrollCalculator();

  @Test
  void testGrossPay_noOvertime() {
    double gross = calculator.calculateGrossPay(30);
    assertEquals(30 * 16.78, gross, 0.001);
  }

  @Test
  void testGrossPay_withOvertime() {
    double gross = calculator.calculateGrossPay(45);
    double expected = 40 * 16.78 + 5 * 16.78 * 1.5;
    assertEquals(expected, gross, 0.001);
  }

  @Test
  void testTaxesAndDeductions() {
    double gross = 1000.0;
    assertEquals(60.0, calculator.calculateSocialSecurityTax(gross), 0.001);
    assertEquals(140.0, calculator.calculateFederalIncomeTax(gross), 0.001);
    assertEquals(50.0, calculator.calculateStateIncomeTax(gross), 0.001);
    assertEquals(10.0, calculator.calculateUnionDues(), 0.001);
    assertEquals(35.0, calculator.calculateInsurance(3), 0.001);
    assertEquals(15.0, calculator.calculateInsurance(2), 0.001);
  }

  @Test
  void testNetPayCalculation() {
    double gross = 1000.0;
    int dependents = 3;
    double net = calculator.calculateNetPay(gross, dependents);
    double expected = gross - (60 + 140 + 50 + 10 + 35);
    assertEquals(expected, net, 0.001);
  }
}
