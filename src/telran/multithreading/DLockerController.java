package telran.multithreading;

public class DLockerController {
    public static void main(String[] args) {
        DLocker thread1 = new DLocker();
        DLocker thread2 = new DLocker();

        thread1.start();
        thread2.start();
    }
}
