package MultiThreading;

import java.util.Random;

public class MultiThreadImplementsDemonstration {

	public static void main (String[] args) {

		MultiThreadImplementsDemonstration demo = new MultiThreadImplementsDemonstration();
		
		Random rn = new Random();
		
		// -- create 5 Thread object
		MultiThreadImplements MTEs[] = new MultiThreadImplements[5];
		for (int i = 0; i < MTEs.length; ++i) {
			// -- each thread has a name, a sleep time in milliseconds, and an owner Object
			MTEs[i] = new MultiThreadImplements("Thread " + i, rn.nextInt(10) * 100, demo);
		}
		
		// -- start the threads
		for (int i = 0; i < MTEs.length; ++i) {
			// -- classes that implement Runnable must construct
			//    a Thread object [anonymously is fine] then start
			//    that object
			new Thread(MTEs[i]).start();
		}
		
		// -- keep the main thread running for awhile
		for (int i = 0; i < 50; ++i) {
			System.out.println("Main " + i);
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				System.out.println("Main thread interrupted");
			}
		}
		System.out.println("Main thread terminating");

	}
}
