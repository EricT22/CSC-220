package ResourceSharing;


public class Consumer extends Thread {

	private BufferInterface buffer;
	
	public Consumer (BufferInterface buffer) {
		// -- call the Thread constructor passing in the desired Thread name
		//    if you remove this you will get a system generated Thread name
		super("Consumer Thread");
		this.buffer = buffer;
	}

	// -- invoked by the JVM when the object is in the Ready
	//    executed when the JVM moves the object into the Running state
	public void run () {	
		
		// -- consume 10 items from the buffer
		//    sleep randomly from 0 to 3 seconds in between
		//    accesses
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep((int)(Math.random() * 3000));
				int s = buffer.getBuffer();
				System.out.println(Thread.currentThread().getName() + " gets " + s);
			}
			catch (InterruptedException ie) {
				System.out.println("Consumer sleep interrupted");
			}
		}
		System.out.println(this.getName() + " terminated.");
	}

}
