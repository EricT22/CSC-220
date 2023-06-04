package ArrayListAssignment;

import RationalNumbers.RationalNumber;

public abstract class RationalNumberAbstract implements ListInterface {

	protected RationalNumber data[];
	protected int capacity;
	protected int size;
	
	public RationalNumberAbstract() {
		this.capacity = 10;
		this.size = 0;
		data = new RationalNumber[10];
	}
	
	public RationalNumberAbstract(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.data = new RationalNumber[capacity];
	}

	
	// -- increase the capacity (if necessary) of the array list
	//    to at least n
	public abstract void ensureCapacity(int n);
	
	// -- deep (object level) comparison of the list held by this to 
	//    the list held by e, returns true if all elements are equal
	//    (and in the same order), false otherwise
	public abstract boolean equals(Object e);
	
	// -- returns a new array list consisting of items from fromindex to
	//    toindex of the existing list.
	//    throws ArrayIndexOutOfBoundsException if fromindex < 0, toindex < 0,
	//    fromindex >= size, toindex >= size, or toindex < fromindex
	public abstract ListInterface subList(int fromindex, int toindex);
	
}