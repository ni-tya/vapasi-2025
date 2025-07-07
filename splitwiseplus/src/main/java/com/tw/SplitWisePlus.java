package com.tw;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;

public class SplitWisePlus {

    private static final Logger LOGGER = Logger.getLogger(SplitWisePlus.class.getName());

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> transactions = new HashMap<>();

        try (InputStream inputStream = SplitWisePlus.class.getClassLoader().getResourceAsStream("input.txt")) {
            if (inputStream == null) {
                LOGGER.severe("File not found: input.txt");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            Pattern pattern = Pattern.compile("^(\\w+)\\s+spent\\s+(\\d+)\\s+for\\s+.+\\s+for\\s+([A-Za-z]+(?:\\s*,\\s*[A-Za-z]+)*)$");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.trim());
                if (!matcher.matches()) {
                    throw new InvalidExpenseFormatException("Invalid input format: " + line);
                }

                String payer = matcher.group(1);
                int amount = Integer.parseInt(matcher.group(2));
                List<String> participants = Arrays.stream(matcher.group(3).split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

                processExpense(payer, amount, participants, transactions);
            }

            printMergedTransactions(transactions);

        } catch (InvalidExpenseFormatException e) {
            LOGGER.severe("Invalid format: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "I/O error while reading file", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error", e);
        }
    }

    static void processExpense(String payer, int amount, List<String> participants,
                               Map<String, Map<String, Integer>> transactions) {
        int share = amount / participants.size();

        for (String participant : participants) {
            if (participant.equals(payer)) continue;
            transactions
                    .computeIfAbsent(participant, k -> new HashMap<>())
                    .merge(payer, share, Integer::sum);
        }
    }

    static void printMergedTransactions(Map<String, Map<String, Integer>> transactions) {
        System.out.println("List of transactions -");

        List<String> outputLines = new ArrayList<>();

        for (String from : transactions.keySet()) {
            for (String to : transactions.get(from).keySet()) {
                int amount = transactions.get(from).get(to);
                outputLines.add(from + " pays " + to + " " + amount);
            }
        }

        outputLines.sort(Comparator.naturalOrder());

        for (String line : outputLines) {
            System.out.println(line);
        }
    }

    public static class InvalidExpenseFormatException extends Exception {
        public InvalidExpenseFormatException(String msg) {
            super(msg);
        }
    }
}
