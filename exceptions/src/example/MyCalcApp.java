package example;

import java.util.Scanner;

public class MyCalcApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        Calculator calculator = new Calculator();
        try {
            System.out.println("Double value: " + calculator.caldouble(number));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class MyArithException extends RuntimeException {
    public MyArithException(String message) {
        super(message);
    }
}

class Calculator {
    double caldouble(int num) {
        if (num == 0) {
            throw new MyArithException("Zero not allowed");
        } else if (num < 0) {
            throw new MyArithException("Negative not allowed");
        } else {
            return num;
        }
    }
}