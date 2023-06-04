package MultiThreading;

import java.util.Random;

public class MultiThreadExtendsDemonstration {

	public static void main (String[] args) {

		// -- construct an object to act as the owner of the threads
		MultiThreadExtendsDemonstration demo = new MultiThreadExtendsDemonstration();
		
		Random rn = new Random();
		
		// -- create 5 Thread object
		MultiThreadExtends MTEs[] = new MultiThreadExtends[5];
		for (int i = 0; i < MTEs.length; ++i) {
			// -- when a class extends the Thread class it can be
			//    constructed directly
			MTEs[i] = new MultiThreadExtends("Thread " + i, rn.nextInt(10) * 100, demo);
		}
		
		// -- start the threads
		for (int i = 0; i < MTEs.length; ++i) {
			MTEs[i].start();
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
