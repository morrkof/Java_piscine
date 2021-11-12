package day03.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    private static int getArg(String str) {
        int arg = 0;
        if (str.startsWith("--arraySize=") || str.startsWith("--threadCount=")) {
            String[] arrStr = str.split("=");
            if (arrStr.length == 2) {
                try {
                    arg = Integer.parseInt(arrStr[1]);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return arg;
    }

    private static int[] getRandomArray(int size) {
        long sum = 0;
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int tmp = random.nextInt(1000);
            arr[i] = tmp;
            sum += tmp;
        }
        System.out.println("Sum: " + sum);
        return arr;
    }

    private static void summator(int[] arr, int threads, int index, long[] sum) {
        int start = 0;
        int finish = 0;
        if ((arr.length % threads) != 0) {
            start = index * (1 + arr.length / threads);
            finish = start + (1 + arr.length / threads);
        } else {
            start = index * (arr.length / threads);
            finish = start + (arr.length / threads);
        }
        if (index + 1 == threads)
            finish = arr.length;
        long result = 0;
        for (int i = start; i < finish ; i++) {
            result += arr[i];
        }
        sum[index] = result;
        System.out.println("Thread " + (index + 1) +
                ": from " + start +
                " to " + (finish - 1) +
                " sum is " + result);
    }

    public static void main(String[] args) throws InterruptedException {
        int arrSize = 0;
        int threadCount = 0;
        if (args.length == 2) {
            arrSize = getArg(args[0]);
            threadCount = getArg(args[1]);
        } else {
            System.out.println("need --arraySize and --threadCount");
            System.exit(-1);
        }
        int[] numbers = getRandomArray(arrSize);
        List<Thread> workers = new ArrayList<>();
        long[] sum = new long[threadCount];
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            int finalThreadCount = threadCount;
            workers.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    summator(numbers, finalThreadCount, finalI, sum);
                }
            }));
        }
        for (Thread t : workers) {
            t.start();
        }
        for (Thread t : workers) {
            t.join();
        }
        long result = 0;
        for (int i = 0; i < threadCount; i++) {
            result += sum[i];
        }
        System.out.println("Sum by threads: " + result);
    }
}
