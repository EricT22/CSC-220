package MultiThreading;

import java.util.Scanner;

public class ThreadStoppingDemonstration {

	public static void main (String[] args) {
		ThreadStopping ts = new ThreadStopping();
		Thread tsThread = new Thread(ts);
		tsThread.start();
		
		Scanner kb = new Scanner(System.in);
		do {
			System.out.print("Enter 1 to stop thread: ");
		} while (kb.nextInt() != 1);
		ts.Stop();
		kb.close();
	}
}
