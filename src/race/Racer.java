package race;

public class Racer extends Thread {
	private final Race race;
	
	Racer(Race race, String name) {
		this.race = race;
		this.setName(name);
	}
	
	@Override
	public void run() {
		System.out.println("Racer: " + this.getName() + " started");
		
		for (int i = 0; i < race.distance; i++) {
			try {
				sleep(race.getSleepingDuration());
				System.out.println("Racer: " + this.getName() + " get " + i);
			} catch (InterruptedException e) {}
		}
		System.out.println("Racer: " + this.getName() + " finished");
		
		if (race.winnerName.equals("")) {
			race.winnerName = this.getName();
		}
	}

}
