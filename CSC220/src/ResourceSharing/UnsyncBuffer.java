package ResourceSharing;


public class UnsyncBuffer implements BufferInterface {

	// -- this is the actual mailbox. It can only hold
	//    one letter at a time
	private int value = -1;

	// -- unsynchronized methods will allow any thread to access the shared
	//    buffer at any time
	
	@Override
	public int getBuffer() {
		return value;
	}

	@Override
	public void setBuffer(int buffer) {
		this.value = buffer;
	}
}
