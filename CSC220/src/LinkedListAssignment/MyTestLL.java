package LinkedListAssignment;

import RationalNumbers.RationalNumber;

public class MyTestLL {
    public static void main(String[] args) {
        ListInterface ll = new RationalNumberLinkedList();

        System.out.println(ll.isEmpty());
        ll.add(0, new RationalNumber(1, 2));
        ll.add(0, new RationalNumber(1, 4));
        ll.add(0, new RationalNumber(1, 8));
        ll.add(0, new RationalNumber(1, 6));
        ll.add(0, new RationalNumber(1, 3));
        ll.add(new RationalNumber(1, 3));
        ll.add(new RationalNumber(1, 5));
        System.out.println(ll.isEmpty());
        RationalNumber[] a = ll.toArray();

        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

        ll.addAll(a);
        System.out.println("adding all");
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }
        ll.clear();
        
        System.out.println("clearing");
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        ll.addAll(a);
        System.out.println("index of 1/5 " + ll.indexOf(new RationalNumber(1, 5)));
        System.out.println("index of 1/3 " + ll.indexOf(new RationalNumber(1, 3)));
        System.out.println("index of 1/1615 " + ll.indexOf(new RationalNumber(1, 1615)));
        
        System.out.println("last index of 1/4 " + ll.lastIndexOf(new RationalNumber(1, 4)));
        System.out.println("last index of 1/3 " + ll.lastIndexOf(new RationalNumber(1, 3)));
        System.out.println("last index of 1/1615 " + ll.lastIndexOf(new RationalNumber(1, 1615)));

        
        RationalNumber removedNum = ll.set(5, new RationalNumber(1, 7));
        System.out.println("Setting index 5 to 1/7 and removing " + removedNum);
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        System.out.println("Contains 1/8 " + ll.contains(new RationalNumber(1, 8)));
        System.out.println("Contains 1/4 " + ll.contains(new RationalNumber(1, 4)));
        System.out.println("Contains 1/1 " + ll.contains(new RationalNumber(1, 1)));

        ll.addAll(1, a);
        System.out.println("adding all at ind 1");
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        removedNum = ll.remove(5);
        System.out.println("Removing " + removedNum);
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        ll.remove(removedNum);
        System.out.println("Again Removing " + removedNum);
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        boolean one = ll.remove(new RationalNumber(1, 3));
        boolean two = ll.remove(new RationalNumber(1, 3));
        boolean three = ll.remove(new RationalNumber(1, 3));
        boolean four = ll.remove(new RationalNumber(1, 3));
        System.out.println("Removal of 1/3(s) " + one + " " + two + " " + three + " " + four);
        for (int i = 0; i < ll.size(); i++){
            System.out.println("\t" + ll.get(i));
        }

        a = ll.toArray();
        RationalNumberLinkedList ll1 = new RationalNumberLinkedList();
        RationalNumberLinkedList ll2 = new RationalNumberLinkedList();
        ll1.addAll(a);
        ll2.addAll(a);
        System.out.println("Equals? " + ll1.equals(ll2));
        for (int i = 0; i < a.length; i++){
            System.out.println("\t" + ll1.get(i) + "\t" + ll2.get(i));
        }

        ll1.set(0, removedNum);
        System.out.println("Equals? " + ll1.equals(ll2));
        for (int i = 0; i < a.length; i++){
            System.out.println("\t" + ll1.get(i) + "\t" + ll2.get(i));
        }

        ListInterface li = ll1.subList(0, 5);
        System.out.println("Sublist");
        for (int i = 0; i < li.size(); i++){
            System.out.println("\t" + li.get(i));
        }
    }
}
