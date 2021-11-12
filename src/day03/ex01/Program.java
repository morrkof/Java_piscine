package day03.ex01;

import java.util.LinkedList;
import java.util.Queue;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        int counter = 0;
        if (args.length == 1)
            counter = getCounter(args[0]);
        int finalCounter = counter;

        Work work = new Work();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    work.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    work.consume(finalCounter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static int getCounter(String arg) {
        int counter = 0;
        if (arg.startsWith("--count=")) {
            String[] arr = arg.split("=");
            if (arr.length == 2) {
                try {
                    counter = Integer.parseInt(arr[1]);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return counter;
    }
}

class Work {
    private Queue<String> buffer = new LinkedList<>();
    boolean flag = true;
    int i = 0;

    synchronized public void produce() throws InterruptedException {
        String msg;
        while (true) {
            while (buffer.size() > 100)
                wait();
            if (flag) {
                msg = "Egg";
                flag = false;
            } else {
                msg = "Hen";
                flag = true;
            }
            buffer.add(msg);
            notify();
        }
    }

    synchronized public void consume(int counter) throws InterruptedException {
        while (true) {
            if (i >= counter * 2)
                System.exit(0);
            while (buffer.size() == 0)
                wait();
            System.out.println(buffer.element());
            buffer.remove();
            i++;
            notify();
        }
    }

}