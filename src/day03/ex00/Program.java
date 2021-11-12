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
                for (int i = finalCounter; i > 0; i--)
                    System.out.println("Egg");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = finalCounter; i > 0; i--)
                    System.out.println("Hen");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        for (int i = finalCounter; i > 0; i--)
            System.out.println("Human");
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
}