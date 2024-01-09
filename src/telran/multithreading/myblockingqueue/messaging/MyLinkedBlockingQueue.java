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
	synchronized public boolean add(E obj) {
		if (this.queue.size() >= capacity) {
			throw new IllegalStateException();
		}
		queue.push(obj);
		return true;
	}

	@Override
	synchronized public boolean offer(E obj) {
		boolean res = true;

		try {
			this.add(obj);
		} catch (IllegalStateException e) {
			res = false;
		}
		return res;
	}

	@Override
	synchronized public void put(E obj) throws InterruptedException {
		while (queue.size() >= capacity) {
			this.wait();
		}
		this.add(obj);
		this.notifyAll();
	}

	@Override
	synchronized public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		var timeRunning = unit.toMillis(timeout);

		while (queue.size() >= capacity && System.currentTimeMillis() <= startTime + timeRunning) {
			wait();
		}
		return this.offer(obj);
	}

	@Override
	synchronized public E remove() {
		return queue.pop();
	}

	@Override
	synchronized public E poll() {
		E res = null;

		try {
			res = this.remove();
		} catch (Exception e) {
		}
		return res;
	}

	@Override
	synchronized public E take() throws InterruptedException {
		while (this.queue.size() == 0) {
			this.wait();
		}
		var res = this.poll();
		this.notifyAll();
		return res;
	}

	@Override
	synchronized public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		var timeRunning = unit.toMillis(timeout);

		while (queue.size() == 0 && System.currentTimeMillis() <= startTime + timeRunning) {
			wait();
		}
		var res = this.poll();
		this.notifyAll();
		return res;
	}

	@Override
	synchronized public E element() {
		return this.queue.element();
	}

	@Override
	synchronized public E peek() {
		E res = null;

		try {
			res = this.element();
		} catch (Exception e) {

		}
		return res;
	}

}
