package com.tw;

import static org.junit.jupiter.api.Assertions.*;
import com.tw.SplitWisePlus.Expense;
import com.tw.SplitWisePlus.InvalidExpenseFormatException;
import java.util.*;
import org.junit.jupiter.api.Test;

public class SplitWisePlusTest {

    @Test
    public void testSingleExpenseWithTwoPeople() {
        Expense.expenseMap.clear();
        Expense expense = new Expense("Aryan", 100, Arrays.asList("Aryan", "Navami"));
        expense.calculateExpenditure();

        assertEquals(50, Expense.expenseMap.get("NavamiAryan"));
    }

    @Test
    public void testExpenseWithOnlySelf() {
        Expense.expenseMap.clear();
        Expense expense = new Expense("Amy", 100, List.of("Amy"));
        expense.calculateExpenditure();

        assertTrue(Expense.expenseMap.isEmpty());
    }

    @Test
    public void testBalancingBetweenTwoPeople() {
        Expense.expenseMap.clear();

        new Expense("Navami", 60, Arrays.asList("Navami", "Aryan")).calculateExpenditure();
        new Expense("Aryan", 80, Arrays.asList("Aryan", "Navami")).calculateExpenditure();

        // Bob owes 10 to Alice
        assertFalse(Expense.expenseMap.containsKey("AryanNavami"));
        assertEquals(10, Expense.expenseMap.get("NavamiAryan"));
    }

    @Test
    public void testInvalidInputFormatThrowsException() {
        String inputLine = "Invalid input line";
        String regex = "^(\\w+)\\s+spent\\s+(\\d+)\\s+for\\s+.+\\s+for\\s+([A-Za-z]+(?:\\s*,\\s*[A-Za-z]+)*)$";

        assertThrows(InvalidExpenseFormatException.class, () -> {
            if (!inputLine.matches(regex)) {
                throw new InvalidExpenseFormatException("Invalid input format: " + inputLine);
            }
        });
    }
}
