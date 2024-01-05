package telran.multithreading;

import java.time.Instant;
import java.util.Random;

public class MyThread extends Thread {
    static Instant startTime;
    Instant endTime;
    private int distance;
    private int max = 5;
    private Random rn = new Random();

    public MyThread(int distance) {
        this.distance = distance;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < distance; i++)
                sleep(rn.nextInt(max + 1));

            this.endTime = Instant.now();
            synchronized (MyController.winnerTable) {
                MyController.winnerTable.add(this);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
