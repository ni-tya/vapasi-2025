package com.tw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitWisePlus {
    private static final Logger LOGGER = Logger.getLogger(SplitWisePlus.class.getName());

    public static void main(String[] args) {
        String inputLine;

        try (InputStream inputStream = SplitWisePlus.class.getClassLoader().getResourceAsStream("input.txt")) {
            if (inputStream == null) {
                LOGGER.severe("File not found: input.txt");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((inputLine = reader.readLine()) != null) {
                String regex = "^(\\w+)\\s+spent\\s+(\\d+)\\s+for\\s+.+\\s+for\\s+([A-Za-z]+(?:\\s*,\\s*[A-Za-z]+)*)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(inputLine);

                if (!matcher.matches()) {
                    throw new InvalidExpenseFormatException("Invalid input format: " + inputLine);
                }

                Expense expense = new Expense(matcher.group(1), Integer.parseInt(matcher.group(2)), Arrays.stream(matcher.group(3).split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList());
                expense.calculateExpenditure();
            }
            Expense.printOutput();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while reading input file", e);
        }
    }

    public static class InvalidExpenseFormatException extends Exception {
        public InvalidExpenseFormatException(String message) {
            super(message);
        }
    }

    static class Expense {
        String payer;
        int amount;
        List<String> payees;
        static Map<String, Integer> expenseMap = new HashMap<>();

        public Expense(String payer, int amount, List<String> payees) {
            this.payer = payer;
            this.amount = amount;
            this.payees = payees;
        }

        public static void printOutput() {
            System.out.println("List of transactions(Merged) -");
            for (Map.Entry<String, Integer> entry : expenseMap.entrySet()) {
                System.out.println(entry.getKey().charAt(0) + " pays " + entry.getKey().charAt(1) + " : " + entry.getValue());
            }
        }

        public void calculateExpenditure() {
            payees.forEach(payee -> {
                if (!payee.equals(payer)) expenseMap.put((payee + payer), amount / payees.size());
                if (expenseMap.containsKey(payer + payee)) {
                    if (expenseMap.get((payee + payer)) > expenseMap.get((payer + payee))) {
                        expenseMap.put((payee + payer), expenseMap.get((payee + payer)) - expenseMap.get((payer + payee)));
                        expenseMap.remove(payer + payee);
                    } else {
                        expenseMap.put((payer + payee), expenseMap.get((payer + payee)) - expenseMap.get((payee + payer)));
                        expenseMap.remove(payee + payer);
                    }
                }
            });
        }
    }
}