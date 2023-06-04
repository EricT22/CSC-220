package ArrayListAssignment;

import RationalNumbers.RationalNumber;

public class TestRationalNumberList {
	public static void main (String[] args) {
		ListInterface rnl = new RationalNumberList();
		System.out.println("Size: " + rnl.size());
		System.out.println("Empty: " + rnl.isEmpty());
		rnl.add(new RationalNumber(8, 16));
		rnl.add(new RationalNumber(4, 16));
		rnl.add(new RationalNumber(5, 16));
		rnl.add(new RationalNumber(2, 16));
		rnl.add(new RationalNumber(1, 16));

		System.out.println("Size: " + rnl.size());
		System.out.println("Empty: " + rnl.isEmpty());
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}

		RationalNumber[] array = rnl.toArray();
		System.out.println("Array:");
		for (int i = 0; i < array.length; ++i) {
			System.out.println("\t" + array[i]);
		}
		
		rnl.remove(new RationalNumber(5, 16));
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
	
		rnl.clear();
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		rnl.addAll(array);
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		RationalNumber old = rnl.set(2,  new RationalNumber(7, 16));
		System.out.println("Replaced value: " + old);
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		rnl.addAll(3, array);
		System.out.println("Addall:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		rnl.add(0, new RationalNumber(16, 16));
		System.out.println("add:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		old = rnl.remove(5);
		System.out.println("Removed value: " + old);
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		System.out.println("First index of: " + new RationalNumber(1, 8) + " is " + rnl.indexOf(new RationalNumber(1, 8)));
		System.out.println("Last index of: " + new RationalNumber(1, 8) + " is " + rnl.lastIndexOf(new RationalNumber(1, 8)));
		
		RationalNumberList l1 = new RationalNumberList();
		RationalNumberList l2 = new RationalNumberList();
		l1.add(new RationalNumber(1, 1));
		l2.add(new RationalNumber(1, 1));
		l1.add(new RationalNumber(2, 2));
		l2.add(new RationalNumber(2, 2));
		System.out.println("l1 == l2: " + l1.equals(l2));
		l1.set(1,  new RationalNumber(1, 2));
		System.out.println("l1 == l2: " + l1.equals(l2));
		l1.remove(1);
		System.out.println("l1 == l2: " + l1.equals(l2));
		
		System.out.println("Change cacity: ");
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		System.out.println();
		l1.ensureCapacity(20);
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}
		
		System.out.println("SubList: ");
		for (int i = 1; i < 10; ++i) {
			l1.add(new RationalNumber(i, 10));
		}
		for (int i = 0; i < l1.size(); ++i) {
			System.out.println("\t" + l1.get(i));
		}
		System.out.println();
		l2 = (RationalNumberList)(l1).subList(3,  5);
		for (int i = 0; i < l2.size(); ++i) {
			System.out.println("\t" + l2.get(i));
		}
		try {
			l2 = (RationalNumberList)(l1).subList(3,  l1.size());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			l2 = (RationalNumberList)(l1).subList(5, 3);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		
		
	}

}