package telran.multithreading;

public class Printer extends Thread {
	private int N_PORTIONS = 10;
	private int N_NUMBERS = 100;
	private int counter = 0;
	private Printer next;
	private int number;

	public Printer(Printer next, int number) {
		this.next = next;
		this.number = number;
	}
	
	
	public void setPrinter(Printer next) {
		this.next = next;
	}

	@Override
	public void run() {
		while (counter < N_NUMBERS) {
			try {
				sleep(0);
			} catch (InterruptedException e) {
				printNumbers();
				next.interrupt();
			}
		}
	}

	private void printNumbers() {
		do {
			counter++;
			System.out.print(this.number);
		} while (counter % N_PORTIONS != 0 && counter < N_NUMBERS);
		System.out.println();

	}
}
