package com.tw;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SplitWisePlusTest {

    @Test
    void testSingleExpenseSplitEvenly() {
        String payer = "A";
        int amount = 100;
        List<String> participants = Arrays.asList("A", "B", "C", "D");
        Map<String, Map<String, Integer>> transactions = new HashMap<>();

        SplitWisePlus.processExpense(payer, amount, participants, transactions);

        assertEquals(1, transactions.get("B").size());
        assertEquals(25, transactions.get("B").get("A"));

        assertEquals(1, transactions.get("C").size());
        assertEquals(25, transactions.get("C").get("A"));

        assertEquals(1, transactions.get("D").size());
        assertEquals(25, transactions.get("D").get("A"));

        assertNull(transactions.get("A"));
    }

    @Test
    void testMultipleExpensesMergeOwedAmount() {
        String payer = "A";
        int amount1 = 100;
        int amount2 = 100;
        List<String> participants = Arrays.asList("A", "B", "C", "D");
        Map<String, Map<String, Integer>> transactions = new HashMap<>();

        SplitWisePlus.processExpense(payer, amount1, participants, transactions);
        SplitWisePlus.processExpense(payer, amount2, participants, transactions);

        assertEquals(50, transactions.get("B").get("A"));
        assertEquals(50, transactions.get("C").get("A"));
        assertEquals(50, transactions.get("D").get("A"));
    }

    @Test
    void testExpenseWithOnlyPayer() {
        String payer = "A";
        int amount = 100;
        List<String> participants = Collections.singletonList("A");
        Map<String, Map<String, Integer>> transactions = new HashMap<>();

        SplitWisePlus.processExpense(payer, amount, participants, transactions);

        assertTrue(transactions.isEmpty());
    }
}
