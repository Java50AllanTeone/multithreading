package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class MyController {
    static ArrayList<MyThread> winnerTable = new ArrayList<>();
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
        nThreads = inputOutput.readInt("Enter number of threads ", "Wrong number", 3, 10);
        distance = inputOutput.readInt("Enter distance ", "Wrong number", 100, 3500);
        Thread[] threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++)
            threads[i] = new MyThread(distance);

        MyThread.startTime = Instant.now();
        threadsStart(threads);

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        System.out.println("Congrats to thread #" + winner);
        for (int i = 0; i < winnerTable.size(); i++) {
            var elem = winnerTable.get(i);
            System.out.println(i + 1 + " " + elem.threadId() + ChronoUnit.MILLIS.between(MyThread.startTime, elem.endTime));
        }
    }

    public static void threadsStart(Thread[] threads) {
        for(Thread th: threads)
            th.start();
    }
}
