package org.anand.tutorials.concurrency;

import java.util.Random;

public class SingleThreaded {

	public static void main(String[] args) throws InterruptedException {
		Buffer buf = new Buffer();
		Random rand = new Random();
		
		while(true) {
			int randomNumber = rand.nextInt(100);
			if(randomNumber > 40) {
				buf.addItem(randomNumber);
			} else {
				int removedItem = buf.removeItem();
			}
			Thread.sleep(100);
		}
	}
}
