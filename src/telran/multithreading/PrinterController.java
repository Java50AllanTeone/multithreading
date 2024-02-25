package telran.multithreading;


public class PrinterController {
	private static int N_PRINTERS = 4;

	public static void main(String[] args) throws InterruptedException {
		Printer[] printers = new Printer[N_PRINTERS];
		
		printers[0] = new Printer(null, 0);
		
		for (int i = printers.length - 1; i > 0; i--) {
			int nextPrinter = (i + 1) % printers.length;
			printers[i] = new Printer(printers[nextPrinter], i);
		}
		printers[0].setPrinter(printers[1]);
		
		for (int i = 0; i < printers.length; i++) {
			printers[i].start();
		}
		printers[0].interrupt();
		
		

	}

}
