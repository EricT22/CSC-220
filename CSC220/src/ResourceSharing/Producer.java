package ResourceSharing;


public class Producer extends Thread {

	private BufferInterface buffer;
	
	public Producer (BufferInterface buffer) {
		// -- call the Thread constructor passing in the desired Thread name
		//    if you remove this you will get a system generated Thread name
		super("Producer Thread");
		this.buffer = buffer;
	}

	// -- invoked by the JVM when the object is in the Ready
	//    executed when the JVM moves the object into the Running state
	@Override
	public void run () {
		
		// -- produce 10 items into the buffer
		//    sleep randomly from 0 to 3 seconds in between
		//    accesses
		for (int i = 0; i < 10; ++i) {
			try {
				Thread.sleep((int)(Math.random() * 3000));
				buffer.setBuffer(i);
				System.out.println(Thread.currentThread().getName() + " sets " + i);
			}
			catch (InterruptedException ie) {
				System.out.println("Producer sleep interrupted");
			}
		}
		System.out.println(this.getName() + " terminated.");
	}

}
