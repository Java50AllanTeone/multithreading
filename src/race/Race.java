package race;

import java.util.Random;

public class Race {
	String winnerName = "";
	Racer[] racers;
	long[] resultTable;
	int distance;
	
	Race(int count, int distance) {
		this.distance = distance;
		getRacers(count);
		
	}
	
	public int getSleepingDuration() {
		return new Random().nextInt(3) + 2;
	}
	
	private void getRacers(int count) {
		racers = new Racer[count];
		resultTable = new long[count];
		
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(this, "Racer: " + i, i);
		}
	}
	
	public void startRacers() {
		for (int i = 0; i < racers.length; i++) {
			racers[i].start();
		}
		waitRacers();
		System.out.println("Winner: " + getWinner().getName());
	}
	
	private void waitRacers() {
		for (int i = 0; i < racers.length; i++) {
			try {
				racers[i].join();
			} catch (InterruptedException e) {}
		}
	}
	
	private Racer getWinner() {
		long curMin = resultTable[0];
		int curInd = 0;
		
		for (int i = 1; i < resultTable.length; i++) {
			if (resultTable[i]< curMin) {
				curMin = resultTable[i];
				curInd = i;
			}
		}
		return racers[curInd];
	}
	
	public void setFinish(Racer racer) {
		resultTable[racer.id] = System.currentTimeMillis();
	}

}
