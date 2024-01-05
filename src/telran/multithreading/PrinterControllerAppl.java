package telran.multithreading;


public class PrinterControllerAppl {
	public static final int COUNT_THREADS = 50;
	public static final int LENGTH = 100;
	public static final int PORTION = 10;

	public static void main(String[] args) {
		Printer[] threads = new Printer[COUNT_THREADS];
		threads[0] = new Printer();

		for (int i = 1; i < threads.length; i++) {
			threads[i] = new Printer();
			threads[i - 1].setNext(threads[i]);
			threads[i - 1].start();
		}

		threads[COUNT_THREADS - 1].setNext(threads[0]);
		threads[COUNT_THREADS - 1].start();
		threads[0].interrupt();
	}

}
