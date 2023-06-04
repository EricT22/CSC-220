package ResourceSharing;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		System.out.print("Synchronized (s) or Unsynchronized (u): ");
		String buffertype = kb.nextLine();
		System.out.print("Daemon (d) or non-Daemon (n)); ");
		String daemon = kb.nextLine();
		kb.close();
		
		// -- construct either a synchronized or unsynchronized buffer as
		//    chosen by the user
		BufferInterface buffer = (buffertype.toLowerCase().equals("s")) ? new SyncBuffer() : new UnsyncBuffer();
		
		// -- construct the two threads with a reference to the shared 
		//    resource, the buffer (put into the created state)
		Consumer consumer = new Consumer(buffer);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
		Producer producer = new Producer(buffer);

		// -- start the threads (put into the ready state)
		producer.setDaemon(daemon.toLowerCase().equals("d"));
		producer.start();
		consumer.setDaemon(daemon.toLowerCase().equals("d"));
		consumer.start();
		
		// -- the main then terminates
		System.out.println("terminating main");
	}
}
