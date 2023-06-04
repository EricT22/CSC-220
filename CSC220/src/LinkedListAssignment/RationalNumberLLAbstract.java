package LinkedListAssignment;

import RationalNumbers.RationalNumber;

public abstract class RationalNumberLLAbstract implements ListInterface {

	// -- Node class for insertion into the linked-list
	//    contains the data item and a reference link to the next Node in the list
	protected class Node {
		protected RationalNumber data;
		protected Node next;
		
		public Node () {
			this.data = new RationalNumber(0, 1);
			this.next = null;
		}
		
		public Node(RationalNumber e) {
			this.data = new RationalNumber(e);
			this.next = null;
		}

		public Node (int n, int d) {
			try {
				this.data = new RationalNumber(n, d);
				this.next = null;
			}
			catch (IllegalArgumentException e) {
				throw e;
			}
		}
		
		@Override
		public String toString() {
			return this.data + "";
		}
	}
	
	protected Node head;
	
	public RationalNumberLLAbstract() {
		// -- head is a reference to a Node but does not hold any data. The linked list actually
		//    starts at head.next. This makes the insert and remove functions much simpler, especially
		//    when inserting at index 0
		this.head = new Node();
	}
	
	// -- deep (object level) comparison of the list held by this to 
	//    the list held by e, returns true if all elements are equal
	//    (and in the same order), false otherwise
	public abstract boolean equals(Object e);
	
	// -- returns a new list consisting of items from fromindex to
	//    toindex of the existing list.
	//    throws IllegalArgumentException if fromindex < 0, toindex < 0,
	//    fromindex >= size, toindex >= size, or toindex < fromindex
	public abstract ListInterface subList(int fromindex, int toindex);
	
}