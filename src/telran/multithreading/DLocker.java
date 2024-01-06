package telran.multithreading;

public class DLocker extends Thread {
    static Object mutex1 = new Object();
    static Object mutex2 = new Object();

    @Override
    public void run() {
        for (;;) {
            f1();
            f2();
        }


    }

    private void f2() {
        synchronized (mutex2) {
            synchronized (mutex1) {

            }
        }
    }

    private void f1() {
        synchronized (mutex1) {
            synchronized (mutex2) {

            }
        }
    }
}
