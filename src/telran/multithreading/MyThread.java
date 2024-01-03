package telran.multithreading;

import java.util.Random;

public class MyThread extends Thread {
    private int distance;
    private int max = 5;
    private Random rn = new Random();
    private int sleepTime = rn.nextInt(max + 1);

    public MyThread(int distance) {
        this.distance = distance;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < distance; i++) {
                sleep(sleepTime);
                System.out.println(this.threadId());
            }

            if (MyController.winner == -1) {
                MyController.winner = this.threadId();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
