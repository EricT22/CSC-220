package ResourceSharing;

/*
 * This synchronization is implemented in Java with a concept called monitors. Only one thread can own a monitor 
 * at a given time. When a thread acquires a lock, it is said to have entered the monitor. All other threads 
 * attempting to enter the locked monitor will be suspended (resource block state) until the first thread exits the monitor.
 * Therefore, it is not possible for two invocations of synchronized methods on the same object to interleave. 
 * 
 * When a synchronized method exits, it automatically establishes a happens-before relationship with any subsequent 
 * invocation of a synchronized method for the same object. This guarantees that changes to the state of the object 
 * are visible to all threads. That is, the object will be completely updated before another thread can obtain
 * the monitor (enter the synchronized method).
 * 
 */
public class SyncBuffer implements BufferInterface {

	// -- this is the actual mailbox. It can only hold
	//    one letter at a time
	private int value = -1;
	
	// -- keep track of the number of values in the buffer
	//    should be 0 or 1 for this example
	private boolean full = false;
	
	// -- synchronized methods block usage of this object by more
	//    than one other object
	//    associates a monitor with this object/method
	@Override
	public synchronized int getBuffer() {

		System.out.println("getBuffer()");
		// -- while the buffer is empty, block the get by sending
		//    the calling thread into the waiting state (releasing the instance lock)
		while (!full) {
			System.out.println("get waiting");
			try {
				// -- release the instance monitor (instance lock) and wait for a notify
				this.wait();
			}
			catch (InterruptedException ie){
				System.out.println("wait state interrupted");
			}			
		}
		// -- sleep will hold onto the instance lock [possibly] sending other threads into the Blocked state
//		try {
//			Thread.sleep(700);
//		} catch (InterruptedException e) {
//		} 
		
		// -- mark buffer empty
		full = false;
		
		// -- send all threads waiting on this monitor to the ready state (from
		//    the waiting state) and let the JVM decide which one goes on to the
		//    running state
		//notifyAll();

		// -- let the JVM decide which thread waiting on this monitor is released
		//    to the ready state
		notify();
		
		return value;
	}

	// -- synchronized methods block usage of this object by more
	//    than one other object
	//    associates a monitor with this object/method
	@Override
	public synchronized void setBuffer(int v) {
		System.out.println("setBuffer()");

		// -- while the buffer is full, block the set by sending
		//    the calling thread into the waiting state (releasing the instance lock)
		while (full) {
			System.out.println("set waiting");
			try {
				// -- release the instance monitor (instance lock) and wait for a notify 
				this.wait();
			}
			catch (InterruptedException ie) {
				System.out.println("wait state interrupted");
			}
		}
		// -- sleep will hold onto the instance lock [possibly] sending other threads into the Blocked state
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		} 
		
		// -- when there is space in the buffer, allow the set
		this.value = v;
		
		// -- mark buffer full
		full = true;
		
		// -- send all threads waiting on this monitor to the ready state (from
		//    the waiting state) and let the JVM decide which one goes on to the
		//    running state
		//notifyAll();
		
		// -- let the JVM decide which thread waiting on this monitor is released
		//    to the ready state
		notify();
		
	}

	
	
}
