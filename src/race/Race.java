package race;

import java.util.ArrayList;
import java.util.Random;

public class Race {
	int baseOffset = 10;
	String[] columns = {"place", "racer number", "time"};
	String title = getTitle();
	
	Racer[] racers;
	ArrayList<Racer> winnerTable;
	int distance;
	long timeStart;
	
	Race(int count, int distance) {
		this.distance = distance;
		getRacers(count);	
	}
	
	public int getSleepingDuration() {
		return new Random().nextInt(3) + 2;
	}
	
	private void getRacers(int count) {
		racers = new Racer[count];
		winnerTable = new ArrayList<>(count);
		
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(this, "Racer: " + i, i);
		}
	}
	
	public void startRacers() {
		timeStart = System.currentTimeMillis();
		for (int i = 0; i < racers.length; i++) {
			racers[i].start();
		}
		waitRacers();
		printWinners();
	}
	
	private void waitRacers() {
		for (int i = 0; i < racers.length; i++) {
			try {
				racers[i].join();
			} catch (InterruptedException e) {}
		}
	}
	
	private void printWinners() {
		System.out.println(title);
		System.out.println("-".repeat(title.length()));
		
		for (int i = 0; i < winnerTable.size(); i++) {
			Racer racer = winnerTable.get(i);
			int place = i + 1;
			long resultTime = racer.timeFinish - timeStart;
			System.out.printf("%2$d%1$s%3$d%1$s%4$d\n", getInnerOffset(place, racer.id, resultTime), place, racer.id, resultTime);
		}
	}
	
	private String getTitle() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < columns.length - 1; i++) {
			builder.append(columns[i]);
			builder.append(" ".repeat(baseOffset));
		}
		builder.append(columns[columns.length - 1]);
		return builder.toString();
	}
	
	private String getInnerOffset(int place, int id, long time) {
		int chars = ("" + place + time + id).length();
		return " ".repeat((title.length() - chars) / (columns.length - 1));
	}
	
	synchronized public void setFinish(Racer racer) {
		winnerTable.add(racer);
	}

}
