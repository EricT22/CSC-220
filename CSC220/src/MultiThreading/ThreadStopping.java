package MultiThreading;

import java.util.Random;

public class ThreadStopping implements Runnable {

	private boolean stop = false;
	
	public void Stop() {
		stop = true;		
	}
	
	@Override
	public void run() {
		Random rn = new Random();
		int count = 0;
		while (!stop) {
			System.out.println("Thread counter = " + count++);
			try {
				Thread.sleep(rn.nextInt(2) * 1000);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}

	}

}
