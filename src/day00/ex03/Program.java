package day00.ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        long grades = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < 42; i++) {
            if (i > 18) {
                ftError("must be less than 18 weeks");
            }
            if (scanner.hasNextInt() && scanner.nextInt() == 42) {
                printResult(grades);
                System.exit(0);
            }
            if (!"Week".equals(scanner.next())) {
                ftError("must be 'Week'");
            } else {
                if (scanner.hasNextInt() && scanner.nextInt() != i) {
                    ftError("wrong order of weeks");
                } else {
                    grades = packGrade(grades, getMinimalGrade(scanner));
                }
            }
        }
    }

    private static void printResult(long grades) {
        if (grades < 1) {
            ftError("500 internal server error");
        }
        int weeks = getNumberOfDigits(grades);
        long denominator = getDenominator(weeks);
        for (int i = 1; i <= weeks; i++) {
            System.out.print("Week ");
            System.out.print(i);
            System.out.print(" ");
            for (int j = 0; j < grades / denominator; j++) {
                System.out.print("=");
            }
            System.out.println(">");
            grades = grades % denominator;
            denominator = denominator / 10;
        }

    }

    private static  long getDenominator(int pow) {
        long result = 1;
        for (int i = 1; i < pow; i++) {
            result *= 10;
        }
        return result;
    }

    private static int getNumberOfDigits(long number) {
        int result = 0;
        while (number > 0) {
            result++;
            number /= 10;
        }
        return result;
    }

    private static long packGrade(long gradePackage, int grade) {
        if (gradePackage != 0)
            gradePackage *= 10;
        gradePackage += grade;
        return gradePackage;
    }

    private static int getMinimalGrade(Scanner scanner) {
        int min = 10;
        for (int i = 0; i < 5; i++) {
            if (!scanner.hasNextInt()) {
                ftError("must be number");
            } else {
                int next = scanner.nextInt();
                if (next > 9 || next < 1) {
                    ftError("must be number between 1 and 9");
                }
                if (next < min)
                    min = next;
            }
        }
        return min;
    }

    private static void ftError(String error) {
        System.out.println("Illegal Argument: " + error);
        System.exit(-1);
    }
}
