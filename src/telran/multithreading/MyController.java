package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class MyController {
    static AtomicLong winner = new AtomicLong(-1);
    private static int nThreads;
    private static int distance;

    public static void main(String[] args) throws InterruptedException {
        InputOutput io = new SystemInputOutput();

        ArrayList<Item> list = new ArrayList<>(List.of(Item.of("New Game", MyController::newGame),
                Item.exit()));
        Menu menu = new Menu("Threads Race", list);
        menu.perform(io);
    }

    private static void newGame(InputOutput inputOutput) {
        winner.set(-1);
        nThreads = inputOutput.readInt("Enter number of threads ", "Wrong number", 3, 10);
        distance = inputOutput.readInt("Enter distance ", "Wrong number", 100, 3500);
        Thread[] threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++)
            threads[i] = new MyThread(distance);

        for (int i = 0; i < threads.length; i++)
            threads[i].start();

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Congrats to thread #" + winner.get());
    }
}
