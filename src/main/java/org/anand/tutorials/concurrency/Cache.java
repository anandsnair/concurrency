package org.anand.tutorials.concurrency;

import java.util.HashMap;
import java.util.Map;

public class Cache {

	Map<String, String> map = new HashMap<>();
	
	Map<String, Object> mapOfLock = new HashMap<>();
	
	public Cache() {
		mapOfLock.put("Thread-0", new Object());
		mapOfLock.put("Thread-1", new Object());

	}
	
	public void updateCache(String key, String value) {
		System.out.println(Thread.currentThread().getName() + 
				" reached update with key " + key + " and value "+ value);
		synchronized (mapOfLock.get(Thread.currentThread().getName())) {
			System.out.println(Thread.currentThread().getName() + " acquired lock");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + 
					" Updated with key " + key + " and value "+ value);

		}
	}
	
}
