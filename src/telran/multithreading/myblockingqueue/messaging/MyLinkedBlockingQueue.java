package telran.multithreading.myblockingqueue.messaging;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	private  LinkedList<E> queue;
    private int capacity;

	public MyLinkedBlockingQueue (int capacity) {
		this.capacity = capacity;
		this.queue = new LinkedList<>();
	}
	@Override
	public synchronized boolean add(E obj) {
		if (this.queue.size() >= capacity) {
			throw new IllegalStateException();
		}
		queue.push(obj);
		return true;
	}

	@Override
	public synchronized boolean offer(E obj) {
		boolean res = true;

		try {
			this.add(obj);
		} catch (IllegalStateException e) {
			res = false;
		}
		return res;
	}

	@Override
	public synchronized void put(E obj) throws InterruptedException {
		while (queue.size() >= capacity) {
			wait();
		}
		this.add(obj);
		notifyAll();
	}

	@Override
	public synchronized boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		var timeRunning = unit.toMillis(timeout);

		while (queue.size() >= capacity && System.currentTimeMillis() <= startTime + timeRunning) {
			wait();
		}
		return this.offer(obj);
	}

	@Override
	public synchronized E remove() {
		return queue.pop();
	}

	@Override
	public synchronized E poll() {
		E res = null;

		try {
			res = this.remove();
		} catch (Exception e) {
		}
		return res;
	}

	@Override
	public synchronized E take() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		return this.poll();
	}

	@Override
	public synchronized E poll(long timeout, TimeUnit unit) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		var timeRunning = unit.toMillis(timeout);

		while (queue.size() == 0 && System.currentTimeMillis() <= startTime + timeRunning) {
			wait();
		}
		return this.poll();
	}

	@Override
	public synchronized E element() {
		return this.queue.element();
	}

	@Override
	public synchronized E peek() {
		E res = null;

		try {
			res = this.element();
		} catch (Exception e) {

		}
		return res;
	}

}
