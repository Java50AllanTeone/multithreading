package telran.multithreading;

public class Printer extends Thread {
	private Printer next;

	@Override
	public void run() {
		int count = 0;

		while (count < PrinterControllerAppl.LENGTH / PrinterControllerAppl.PORTION) {
			try {
				join();
			} catch (InterruptedException e) {
				System.out.println((this.threadId() + " ").repeat(PrinterControllerAppl.PORTION));
				count++;
				next.interrupt();
			}
		}
	}

	public void setNext(Printer next) {
		this.next = next;
	}
}
