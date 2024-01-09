package telran.multithreading.mbox.blocking.messaging;

public interface MessageBox {
	void put(String message);
	String take() throws InterruptedException ;
	String pull();
}
