package telran.multithreading.mbox.blocking;

import telran.multithreading.mbox.blocking.consumer.Receiver;
import telran.multithreading.mbox.blocking.messaging.*;
import telran.multithreading.mbox.blocking.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxBlockingQueue();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		startReceivers(messageBox, receivers);
		sender.join();
		stopReceivers(receivers);

	}

	private static void stopReceivers(Receiver[] receivers) {
		for(Receiver receiver: receivers) {
			receiver.interrupt();
		}
		
	}

	private static void startReceivers(MessageBox messageBox, Receiver[] receivers) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			receivers[i] = new Receiver(messageBox);
			receivers[i].start();
		}
	}

}
