package MultiThreading;

// -- you can either extend Thread or implement Runnable
//    since Java only allows single inheritance (only extend one object)
//    but you can implement as many interfaces as you like. Thus, you
//    have to use the runnable interface
public class MultiThreadExtends extends Thread {

	// -- each thread instance will have a name
	String threadname;
	// -- this will be used to cause the instance 
	//    to sleep for a specified amount of time
	int sleeptime;
	// -- in case the thread needs to communicate with the object
	//    that created it (i.e. to send information to a GUI)
	//    This can be a specific class type, rather than an Object,
	//    to support a particular application need
	Object owner;
	
	// -- each thread has a name, a sleep time in milliseconds, and an owner Object
	public MultiThreadExtends(String threadname, int sleeptime, Object owner) {
		this.threadname = threadname;
		this.sleeptime = sleeptime;
		this.owner = owner;
	}
	
	
	@Override
	// -- the run function is analogous to public static void main(String[] args)
	//    that is, it is the entry point for the Thread object (or the runnable interface)
	public void run() {
		System.out.println(threadname + " starting.");
		try {
			// -- loop 10 times printing the thread name and then sleeping
			for (int i = 0; i < 10; ++i) {
				Thread.sleep(sleeptime);
				System.out.println("In " + threadname + ", count is " + i);
			}
		} 
		catch (InterruptedException e) {
			System.out.println("sleep interrupted");
		}
		System.out.println(threadname + " terminating");

	}
	

}
