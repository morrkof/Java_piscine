package day00.ex04;

import java.util.Scanner;

public class Program {

    private static void insertSym(int [][] arr, int symbol, int count) {
        int i = 0;
        while (i < 10 && (arr[0][i] > count || (arr[0][i] == count && arr[1][i] < symbol)))
            i++;
        if (i >= 10)
            return;
        for (int j = 9; j > i; j--) {
            arr[0][j] = arr[0][j - 1];
            arr[1][j] = arr[1][j - 1];
        }
        arr[0][i] = count;
        arr[1][i] = symbol;
    }

    private static void printDigit(int num) {
        if (num < 10) {
            System.out.print(num + "  ");
        } else if (num < 100) {
            System.out.print(num + " ");
        }
    }

    private static void printHistogram(int [][] arr) {
        final String hash = "#  ";
        double coeff = arr[0][0] / 10.0;
        if (coeff == 0)
            return;
        for (int i = 11; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (arr[0][j] != 0) {
                    if (arr[0][j] < i * coeff) {
                        if (arr[0][j] >= (i-1) * coeff)
                            printDigit(arr[0][j]);
                    } else
                        System.out.print(hash);
                }
            }
            System.out.print("\n");
        }
        for (int i = 0; i < 10; i++) {
            if (arr[1][i] != 0)
                System.out.print((char)arr[1][i] + "  ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        char [] arrLine = line.toCharArray();
        int counter = 0;

        int[] arr = new int[65536];
        for (int i = 0; i < line.length(); i++) {
            if (arr[(int) arrLine[i]] == 0)
                counter++;
            arr[(int) arrLine[i]]++;
        }
        int j = 0;
        int [][] topSymbols = new int[2][10];
        for (int i = 0; i < counter; i++) {
            while (arr[j] == 0)
                j++;
            insertSym(topSymbols, j, arr[j]);
            j++;
        }
        printHistogram(topSymbols);
    }
}
