package telran.multithreading;

import java.util.Random;

public class MyController {
    static long winner = -1;

    public static void main(String[] args) throws InterruptedException {
        Thread r1 = new MyThread(100);
        Thread r2 = new MyThread(100);
        Thread r3 = new MyThread(100);
        Thread r4 = new MyThread(100);
        Thread r5 = new MyThread(100);

        r1.start();
        r2.start();
        r3.start();
        r4.start();
        r5.start();
        r1.join();
        r2.join();
        r3.join();
        r4.join();
        r5.join();

        System.out.println(winner + "winner");
    }
}
