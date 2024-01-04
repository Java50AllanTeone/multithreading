package telran.multithreading;


public class PrinterControllerAppl {
	public static final int LENGTH = 100;
	public static final int PORTION = 10;

	public static void main(String[] args) {
		Printer p1 = new Printer();
		Printer p2 = new Printer();
		Printer p3 = new Printer();
		Printer p4 = new Printer();
		Printer p5 = new Printer();


		p1.setNext(p2);
		p2.setNext(p3);
		p3.setNext(p4);
		p4.setNext(p5);
		p5.setNext(p1);

		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();

		p1.interrupt();
	}

}
