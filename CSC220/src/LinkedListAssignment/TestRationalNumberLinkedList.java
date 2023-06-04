package LinkedListAssignment;

import RationalNumbers.RationalNumber;

public class TestRationalNumberLinkedList {

	public static void main(String[] args) {
		RationalNumberLinkedList rnll = new RationalNumberLinkedList();
		System.out.println("Size: " + rnll.size());
		System.out.println("Empty: " + rnll.isEmpty());
		for (int i = 0; i <= 5; ++i) {
			rnll.add(i, new RationalNumber(1, (int)Math.pow(2, i)));
		}

		System.out.println(rnll);
		System.out.println("Removed: " + rnll.remove(0));
		System.out.println(rnll);
		System.out.println("Removed: " + rnll.remove(2));
		System.out.println(rnll);
		System.out.println("Removed: " + rnll.remove(3));
		System.out.println(rnll);
		System.out.println("Contains " + new RationalNumber(1, 8) + ": " + rnll.contains(new RationalNumber(1, 8)));
		RationalNumber rna[] = rnll.toArray();
		for (RationalNumber rn : rna) {
			System.out.print(rn + " ");
		}
		System.out.println();
		rnll.add(new RationalNumber(1, 1));
		System.out.println(rnll);
		rnll.addAll(rna);
		System.out.println(rnll);
		rnll.addAll(1, rna);
		System.out.println(rnll);
		try {
			rnll.addAll(27, rna);
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println(ex);
		}
		
		rnll.remove(new RationalNumber(1, 2));
		System.out.println(rnll);
		
		RationalNumberLinkedList rnl0 = new RationalNumberLinkedList();
		RationalNumberLinkedList rnl1 = new RationalNumberLinkedList();
		for (int i = 0; i < 5; ++i) {
			rnl0.add(new RationalNumber(i, 5));
			rnl1.add(new RationalNumber(i, 5));
		}
		System.out.println("Equals: " + rnl0.equals(rnl1));
		rnl0.remove(0);
		System.out.println("Equals: " + rnl0.equals(rnl1));
		System.out.println("Index of " + new RationalNumber(1, 16) + ": " + rnll.indexOf(new RationalNumber(1, 16)));
		System.out.println("Index of " + new RationalNumber(1, 16) + ": " + rnll.lastIndexOf(new RationalNumber(1, 16)));

		RationalNumber rn4 = rnll.get(4);
		System.out.println("Index 4: " + rn4);
		rn4 = rnll.set(4, new RationalNumber(2, 1));
		System.out.println(rnll);
		rnll.clear();
		System.out.println("Empty: " + rnll.isEmpty());
	}
	

}