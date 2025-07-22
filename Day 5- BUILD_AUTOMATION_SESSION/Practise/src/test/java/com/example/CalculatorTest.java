package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        assertEquals(10, calculator.add(7, 3));
    }

    @Test
    public void testSubtraction() {
        assertEquals(4, calculator.subtract(9, 5));
    }

    @Test
    public void testMultiplication() {
        assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    public void testDivision() {
        assertEquals(5, calculator.divide(10, 2));
    }

    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
        assertEquals("Division by zero", exception.getMessage());
    }
}
