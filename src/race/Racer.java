package race;

public class Racer extends Thread {
	private final Race race;
	int id;
	
	Racer(Race race, String name, int id) {
		this.race = race;
		this.setName(name);
		this.id = id;
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
		race.setFinish(this);
	}

}
