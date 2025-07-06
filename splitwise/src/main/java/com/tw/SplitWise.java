package com.tw;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitWise {
    private static final Logger LOGGER = Logger.getLogger(SplitWise.class.getName());

    public static void main(String[] args) {
        String line;
        try (InputStream inputStream = SplitWise.class.getClassLoader().getResourceAsStream("input.txt")) {
            if (inputStream == null) {
                LOGGER.severe("File not found: input.txt");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("List of transactions -");
            while ((line = reader.readLine()) != null) {
                calculateExpenses(line);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while reading input file", e);
        }
    }

    static void calculateExpenses(String inputLine) throws InvalidExpenseFormatException {
        String regex = "^(\\w+)\\s+spent\\s+(\\d+)\\s+for\\s+.+\\s+for\\s+([A-Za-z]+(?:\\s*,\\s*[A-Za-z]+)*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputLine);

        if (!matcher.matches()) {
            throw new InvalidExpenseFormatException("Invalid input format: " + inputLine);
        }

        if (matcher.matches()) {
            String payer = matcher.group(1);
            int amount = Integer.parseInt(matcher.group(2));
            String payeesStr = matcher.group(3);

            List<String> payees = Arrays.stream(payeesStr.split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList();

            Expense expense = new Expense(payer, payees, amount);
            int individualAmount = expense.getAmount() / expense.getPayees().size();

            expense.getPayees().stream()
                    .filter(p -> !p.equals(expense.getPayer()))
                    .forEach(p -> System.out.println(p + " pays " + expense.getPayer() + " " + individualAmount));
        } else {
            LOGGER.warning("Could not parse input line: " + inputLine);
        }
    }

    static class Expense {
        String payer;
        int amount;
        List<String> payees;

        public Expense(String payer, List<String> payees, int amount) {
            this.payer = payer;
            this.payees = payees;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public List<String> getPayees() {
            return payees;
        }

        public String getPayer() {
            return payer;
        }

        @Override
        public String toString() {
            return "Expense{" + "payer='" + payer + '\'' + ", amount=" + amount + ", payees=" + payees + '}';
        }
    }

    public static class InvalidExpenseFormatException extends Exception {
        public InvalidExpenseFormatException(String message) {
            super(message);
        }
    }
}

