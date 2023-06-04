package ArrayListAssignment;

import RationalNumbers.RationalNumber;

public class MyTestList {
    public static void main(String[] args) {
        ListInterface rnl = new RationalNumberList();
		System.out.println("Size: " + rnl.size());
		System.out.println("Empty: " + rnl.isEmpty());
		
        rnl.add(new RationalNumber(12, 16));
		rnl.add(new RationalNumber(4, 16));
		rnl.add(new RationalNumber(5, 16));

        System.out.println("Size: " + rnl.size());
		System.out.println("Empty: " + rnl.isEmpty());
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}

        try{
            System.out.println(rnl.get(rnl.size()));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("nope");
        }

        RationalNumber[] array = rnl.toArray();
		System.out.println("Array:");
		for (int i = 0; i < array.length; ++i) {
			System.out.println("\t" + array[i]);
		}

        System.out.println("Contains 1/2: ");
        System.out.println("\t" + rnl.contains(new RationalNumber(1, 2)));

        System.out.println("Contains 1/4: ");
        System.out.println("\t" + rnl.contains(new RationalNumber(1, 4)));
        

        System.out.println("Index of 5/16:");
        System.out.println("\t" + rnl.indexOf(new RationalNumber(5, 16)));

        System.out.println("Index of 5/17:");
        System.out.println("\t" + rnl.indexOf(new RationalNumber(5, 17)));
        
        rnl.remove(new RationalNumber(3, 4));
		System.out.println("ArrayList:");
		for (int i = 0; i < rnl.size(); ++i) {
			System.out.println("\t" + rnl.get(i));
		}

        System.out.println("Index of 5/16:");
        System.out.println("\t" + rnl.indexOf(new RationalNumber(5, 16)));

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

        // rnl.addAll(array);
        // rnl.addAll(array);
        // rnl.add(new RationalNumber(1,3));
		// System.out.println("ArrayList:");
		// for (int i = 0; i < rnl.size(); ++i) {
		// 	System.out.println("\t" + rnl.get(i));
		// }

        // rnl.add(new RationalNumber(1,3));
		// System.out.println("ArrayList:");
		// for (int i = 0; i < rnl.size(); ++i) {
		// 	System.out.println("\t" + rnl.get(i));
		// }

        try {
            RationalNumber old = rnl.set(rnl.size()-1,  new RationalNumber(7, 16));
            System.out.println("Replaced value: " + old);
            System.out.println("ArrayList:");
            for (int i = 0; i < rnl.size(); ++i) {
                System.out.println("\t" + rnl.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            rnl.add(1, new RationalNumber(1,2));
            System.out.println("Add at index:");
            for (int i = 0; i < rnl.size(); ++i) {
                System.out.println("\t" + rnl.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }    
        
        try{
            rnl.addAll(3, array);
            System.out.println("Addall:");
            for (int i = 0; i < rnl.size(); ++i) {
                System.out.println("\t" + rnl.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }

        // try{
        //     RationalNumber old = rnl.remove(5);
        //     System.out.println("Removed value: " + old);
        //     System.out.println("ArrayList:");
        //     for (int i = 0; i < rnl.size(); ++i) {
        //         System.out.println("\t" + rnl.get(i));
        //     }
        // } catch (ArrayIndexOutOfBoundsException e){
        //     System.out.println(e);
        // }

        // System.out.println("First index of: " + new RationalNumber(1, 4) + " is " + rnl.indexOf(new RationalNumber(1, 4)));
		// System.out.println("Last index of: " + new RationalNumber(1, 4) + " is " + rnl.lastIndexOf(new RationalNumber(1, 4)));
		

        // RationalNumberList l1 = new RationalNumberList();
		// RationalNumberList l2 = new RationalNumberList();
		// l1.add(new RationalNumber(1, 1));
		// l2.add(new RationalNumber(1, 1));
		// l1.add(new RationalNumber(2, 2));
		// l2.add(new RationalNumber(2, 2));
		
        // for (int i = 0; i < l1.size(); ++i) {
        //     System.out.println("\t" + l1.get(i));
        // }
        // for (int i = 0; i < l2.size(); ++i) {
        //     System.out.println("\t" + l2.get(i));
        // }
        // System.out.println("l1 == l2: " + l1.equals(l2));

        

		// l1.set(1,  new RationalNumber(1, 2));
		
        // for (int i = 0; i < l1.size(); ++i) {
        //     System.out.println("\t" + l1.get(i));
        // }
        // for (int i = 0; i < l2.size(); ++i) {
        //     System.out.println("\t" + l2.get(i));
        // }
        
        // System.out.println("l1 == l2: " + l1.equals(l2));
		
        // l1.remove(1);
		// for (int i = 0; i < l1.size(); ++i) {
        //     System.out.println("\t" + l1.get(i));
        // }
        // for (int i = 0; i < l2.size(); ++i) {
        //     System.out.println("\t" + l2.get(i));
        // }
        // System.out.println("l1 == l2: " + l1.equals(l2));

        // System.out.println("Change capacity: ");
		// l1.ensureCapacity(20);
		// for (int i = 0; i < l1.size(); ++i) {
		// 	System.out.println("\t" + l1.get(i));
		// }

        // System.out.println("OG List: ");
        // for (int i = 1; i < 7; ++i) {
		// 	l1.add(new RationalNumber(i, 10));
		// }
		// for (int i = 0; i < l1.size(); ++i) {
		// 	System.out.println("\t" + l1.get(i));
		// }

        // try{
        //     System.out.println("SubList: ");
        //     l2 = (RationalNumberList)(l1).subList(4,  6);
        //     for (int i = 0; i < l2.size(); ++i) {
        //         System.out.println("\t" + l2.get(i));
        //     }
        // } catch (IllegalArgumentException e){
        //     System.out.println("no sublist for you");
        // }
    }
}
