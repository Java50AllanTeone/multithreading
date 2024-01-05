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

        threadsStart(threads);
        threadsJoin(threads);

        printTable(5, 10);


    }

    public static void threadsStart(Thread[] threads) {
        MyThread.startTime = Instant.now();

        for(Thread th: threads)
            th.start();
    }

    public static void threadsJoin(Thread[] threads) {
        for(Thread th: threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void printTable(int headerInt, int interval) {
        String headerSpace = " ".repeat(headerInt);
        String space = " ".repeat(interval);
        String header = String.format("place%sracer number%stime", headerSpace, headerSpace);
        System.out.println(header);

        for (int i = 0; i < winnerTable.size(); i++) {
            var elem = winnerTable.get(i);
            String winnerLine = String.format("%d%s%d%s%d", i + 1, space, elem.threadId(), space, ChronoUnit.MILLIS.between(MyThread.startTime, elem.endTime));
            System.out.println(winnerLine);
        }

    }
}
