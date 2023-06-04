package LinkedListAssignment;

import RationalNumbers.RationalNumber;

public interface ListInterface {

	// -- returns the number of items in the list
	public int size();

	// -- returns true if the list contains no items
	public boolean isEmpty();

	// -- returns true if the list contains the value of e
	public boolean contains(RationalNumber e);

	// -- returns an array of RationalNumber the size of 
	//    the list size
	public RationalNumber[] toArray();

	// -- adds the RationalNumber e to the end of the list,
	//    increasing the size if necessary, return true if successful
	public boolean add(RationalNumber e);

	// -- add e to the list at index,
	//    throws ArrayIndexOutOfBoundsException if (index < 0) or (index > size)
	public void add(int index, RationalNumber e);

	// -- add all items to the end of the list,
	//    increasing size as necessary
	//    return true
	public boolean addAll(RationalNumber[] e);

	// -- insert all items at specified index
	//    throws ArrayIndexOutOfBoundsException if (index < 0) or (index > size)
	//    return true
	public boolean addAll(int index, RationalNumber[] e);

	// -- removes the first instance of e from the list,
	//    decreasing the size and moving all items "below" e
	//    up one index position;
	//    return true if item is successfully removed, false otherwise (e not in the list)
	public boolean remove(RationalNumber e);

	// -- removes the first instance of e from the list,
	//    decreasing the size and moving all items "below" index
	//    up one index position;
	//    throws ArrayIndexOutOfBoundsException if (index < 0) or (index >= size)
	//    returns the removed item
	public RationalNumber remove(int index);
	
	// -- removes all RationalNumbers from the list, sets size to 0
	public void clear();

	// -- returns the Rational Number at index,
	//    throws ArrayIndexOutOfBoundsException if (index < 0) or (index >= size)
	public RationalNumber get(int index);

	// -- set the value of the item at index to e,
	//    throws ArrayIndexOutOfBoundsException if (index < 0) or (index >= size)
	//    return the element originally in position index
	public RationalNumber set(int index, RationalNumber e);

	// -- return the index of the first occurrence of e, -1 if e is not in the list
	public int indexOf(RationalNumber e);

	// -- return the index of the last occurrence of e, -1 if e is not in the list
	public int lastIndexOf(RationalNumber e);


}