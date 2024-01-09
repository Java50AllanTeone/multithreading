package telran.multithreading.garage.modeling;

import java.util.concurrent.*;

import telran.multithreading.garage.dto.Car;

public class Worker extends Thread {
	int nCars;
	BlockingQueue<Car> cars;
	public Worker(BlockingQueue<Car> cars) {
		this.cars = cars;
	}
	@Override
	public void run() {
		Car car = null;
		try {
			while(true) {
				car = cars.take();
				processingCar(car);
			}
		} catch (InterruptedException e) {
			
		}
	}
	private void processingCar(Car car) throws InterruptedException {
		sleep(car.repairTime());
		nCars++;
	}
}
