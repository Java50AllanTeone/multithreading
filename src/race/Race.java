package race;

import java.util.Random;

public class Race {
	String winnerName = "";
	Racer[] racers;
	int distance;
	int sleepingDuration;
	
	Race(int count, int distance) {
		this.distance = distance;
		sleepingDuration = new Random().nextInt(3) + 2;
		getRacers(count);
		
	}
	
	private void getRacers(int count) {
		racers = new Racer[count];
		
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(this, "Racer: " + i);
		}
	}
	
	public void startRacers() {
		for (int i = 0; i < racers.length; i++) {
			racers[i].start();
		}
		waitRacers();
		System.out.println("Winner: " + winnerName);
	}
	
	private void waitRacers() {
		for (int i = 0; i < racers.length; i++) {
			try {
				racers[i].join();
			} catch (InterruptedException e) {}
		}
	}

}
