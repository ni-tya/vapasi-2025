package com.tw;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SplitWiseTest {

    @Test
    void testValidExpenseInput() {
        String input = "A spent 100 for Snacks for A, B, C, D";

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            SplitWise.calculateExpenses(input);
        } catch (SplitWise.InvalidExpenseFormatException e) {
            fail("Valid input should not throw exception");
        }

        String result = output.toString().trim();

        assertTrue(result.contains("B pays A 25"));
        assertTrue(result.contains("C pays A 25"));
        assertTrue(result.contains("D pays A 25"));
    }

    @Test
    void testInvalidExpenseInput() {
        String badInput = "B spent 500 for Taxi for";

        Exception exception = assertThrows(SplitWise.InvalidExpenseFormatException.class, () -> SplitWise.calculateExpenses(badInput));

        String expectedMessage = "Invalid input format";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testEmptyPayeesInput() {
        String badInput = "A spent 300 for Food for , , ,";

        Exception exception = assertThrows(SplitWise.InvalidExpenseFormatException.class, () -> SplitWise.calculateExpenses(badInput));

        assertTrue(exception.getMessage().contains("Invalid input format"));
    }
}
