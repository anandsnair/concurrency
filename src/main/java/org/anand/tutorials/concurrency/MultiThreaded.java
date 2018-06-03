package org.anand.tutorials.concurrency;

import java.util.Random;

public class MultiThreaded {

	class Thread1 implements Runnable {
		
		Buffer buffer;
		Random rand = new Random();

		public Thread1(Buffer buffer) {
			this.buffer = buffer;
		}
		
		public void run() {
			while(true) {
				try {
					buffer.addItem(rand.nextInt(100));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	class Thread2 implements Runnable {

		Buffer buffer;

		public Thread2(Buffer buffer) {
			this.buffer = buffer;
		}

		public void run() {
			while(true) {
				try {
					buffer.removeItem();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		MultiThreaded multiThreaded = new MultiThreaded();
		multiThreaded.startWorking();
	}
	
	public void startWorking() {
		Buffer buf = new Buffer();
		Runnable r1 = new Thread1(buf);
		Runnable r2 = new Thread2(buf);
		
		Thread thread1 = new Thread(r1);
		Thread thread2 = new Thread(r2);
		thread1.start();
		thread2.start();
	}
}
