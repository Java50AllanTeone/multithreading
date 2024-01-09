package telran.multithreading.mbox.condition.messaging;

public interface MessageBox {
	void put(String message);
	String take() throws InterruptedException ;
	String pull();
}
