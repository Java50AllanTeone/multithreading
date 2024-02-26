package race;

public class Racer extends Thread {
	private final Race race;
	int id;
	long timeFinish;
	
	Racer(Race race, String name, int id) {
		this.race = race;
		this.setName(name);
		this.id = id;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < race.distance; i++) {
			try {
				sleep(race.getSleepingDuration());
			} catch (InterruptedException e) {}
		}
		race.setFinish(this);
	}
	
	protected void setTimeFinish() {
		timeFinish = System.currentTimeMillis();
	}

}
