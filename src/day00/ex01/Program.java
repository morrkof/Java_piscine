package day00.ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int number = 0;
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            number = scanner.nextInt();
            scanner.close();
        } else {
            System.err.println("Illegal Argument");
            scanner.close();
            System.exit(-1);
        }

        if (number < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            int count = 1;
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    System.out.println("false " + count);
                    System.exit(0);
                }
                count++;
            }
            System.out.println("true " + count);
        }
    }
}
