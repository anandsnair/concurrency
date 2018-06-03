package org.anand.tutorials.concurrency;

public class Buffer {

	int capacity = 10;
	volatile int currentPointer = -1;
	int[] array = new int[capacity];
	Object lock = new Object();
	
	public void addItem(int item) throws InterruptedException {
		synchronized (lock) {
			System.out.println("Thread "+ Thread.currentThread().getName() +" acquired lock");
			if(currentPointer < capacity-1) {
				insert(item);
			} else {
				lock.wait();
				System.out.println("Reached max and hence Waiting ...");
				insert(item);
				System.out.println("Waiting Over !!.. Now I can insert");
			}
			System.out.println("Current Pointer="+currentPointer);
			System.out.println("Successfully inserted item "+ item +" at "+currentPointer);
			lock.notifyAll();
		}
	}
	
	public int removeItem() throws InterruptedException {
		synchronized (lock) {
			System.out.println("Thread "+ Thread.currentThread().getName() +" acquired lock");
			int item = -1;
			System.out.println("Current Pointer="+currentPointer);
			if(currentPointer > -1) {
				item = remove();
			} else {
				System.out.println("Waiting ... ");
				lock.wait();
				System.out.println("Waiting over !!!");
				item = remove();
			}
			lock.notifyAll();
			return item;
		}
		
	}
	
	private void insert(int item) {
		array[++currentPointer] = item; 
		System.out.println("Inserting item "+item +" at "+currentPointer + " by Thread " +Thread.currentThread().getName());

	}
	
	private int remove() {
		int item = array[currentPointer];
		System.out.println("Removing item "+item +" at "+ currentPointer + " by Thread " +Thread.currentThread().getName());
		--currentPointer;
		return item;
	}
}
