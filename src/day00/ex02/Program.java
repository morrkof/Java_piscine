package day00.ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int coffeeCounter = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number == 42) {
                    System.out.println("Count of coffee-request - " + coffeeCounter);
                    scanner.close();
                    System.exit(0);
                } else {
                    if (isPrime(sumOfDigits(number))) {
                        coffeeCounter++;
                    }
                }
            }
        }
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        } else if (number == 2) {
            return true;
        } else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    private static int sumOfDigits(int number) {
        int result = 0;
        while(number > 0) {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }
}
