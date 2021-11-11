package day03.ex00;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        int counter = 0;
        if (args.length == 1)
            counter = getCounter(args[0]);
        int finalCounter = counter;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printMessage(finalCounter, "Egg");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printMessage(finalCounter, "Hen");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        printMessage(finalCounter, "Human");
    }

    private static int getCounter(String arg) {
        int counter = 0;
        if (arg.startsWith("--count=")) {
            String [] arr = arg.split("=");
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

    private static void printMessage(int i, String msg) {
        for (; i > 0; i--)
            System.out.println(msg);
    }
}