package telran.multithreading;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

import java.util.ArrayList;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Receiver> receivers = new ArrayList<>();
		MessageBox messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		for(int i = 0; i < N_RECEIVERS; i++) {
			receivers.add(new Receiver(messageBox));
			receivers.get(receivers.size() - 1).start();
		}

		sender.join();
		endReceivers(messageBox, receivers);
	}

	public static void endReceivers(MessageBox messageBox, ArrayList<Receiver> receivers) {
		while(true) {
			if (messageBox.pull() == null)
				break;
		}
		for (var receiver : receivers) {
			receiver.interrupt();
		}
	}

}
