package org.anand.tutorials.concurrency;

import java.util.Random;

public class MultiThreadedWithCacheUpdate {

	public String[] keys = {"key1", "key2","key3","key4","key5","key6","key7","key8","key9","key10","key11"};
	public String[] values = {"value1", "value2","value3","value4","value5","value6","value7","value8","value9","value10","value11"};

	class Thread1 implements Runnable {
		
		Cache cache;
		Random rand = new Random();

		public Thread1(Cache cache) {
			this.cache = cache;
		}
		
		public void run() {
			while(true) {
				try {
					int value = rand.nextInt(10);
					cache.updateCache(keys[value], values[value]);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	class Thread2 implements Runnable {

		Cache cache;
		Random rand = new Random();

		public Thread2(Cache cache) {
			this.cache = cache;
		}

		public void run() {
			while(true) {
				try {
					
					int value = rand.nextInt(10);
					cache.updateCache(keys[value], values[value]);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		MultiThreadedWithCacheUpdate multiThreaded = new MultiThreadedWithCacheUpdate();
		multiThreaded.startWorking();
	}
	
	public void startWorking() {
		Cache cache = new Cache();
		Runnable r1 = new Thread1(cache);
		Runnable r2 = new Thread2(cache);
		
		Thread thread1 = new Thread(r1);
		Thread thread2 = new Thread(r2);
		thread1.start();
		thread2.start();
	}
}
