package LinkedLists;

public class LinkedList {
    private class Node {
        private int data;
        private Node next;
    
        public Node(){
            data = 0;
            next = null;
        }
    
        public Node(int data){
            this.data = data;
            next = null;
        }

        @Override
        public String toString(){
            return this.data + "";
        }
    }

    private Node head;

    public LinkedList(){
        head = new Node();
    }

    public void add(int index, int e) throws IndexOutOfBoundsException{
        if (index < 0){
            throw new IndexOutOfBoundsException("Index < 0");
        }
        
        Node ptr = head;

        // -- find the point before the insertion point
        try {
            for (int i = 0; i < index; i++){
                ptr = ptr.next;
            }
        } catch (NullPointerException ex){
            throw new IndexOutOfBoundsException("Index > size");
        }

        // -- construct new node
        Node n = new Node(e);

        // -- assign new node next link
        n.next = ptr.next;

        // -- assign insertion point link
        ptr.next = n;
    }

    public int remove(int index) throws IndexOutOfBoundsException{
        if (index < 0){
            throw new IndexOutOfBoundsException("Index < 0");
        }

        Node ptr = head;

        try{
            for (int i = 0; i < index; i++){
                ptr = ptr.next;
            }

            int i = ptr.next.data;
            ptr.next = ptr.next.next;
            return i;
        } catch (NullPointerException ex){
            throw new IndexOutOfBoundsException("Index > 0");
        }
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof LinkedList){
            LinkedList ll = (LinkedList) o;

            if (this.size() == ll.size()){
                Node ptr = head.next;
                Node llPtr = ll.head.next;

                while (ptr != null){
                    if (ptr.data != llPtr.data){
                        return false;
                    }
                    ptr = ptr.next;
                    llPtr = llPtr.next;
                }
                return true;
            }
        }
        return false;
    }

    public int size(){
        int size = 0;

        Node ptr = head.next;

        while (ptr != null){
            size++;
            ptr = ptr.next;
        }
        return size;
    }

    public void clear(){
        head.next = null;
    }

    public boolean contains(int i){
        Node ptr = head.next;
        while (ptr != null){
            if (ptr.data == i){
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    @Override
    public String toString(){
        String s = "";
        Node ptr = head.next;
        while (ptr != null){
            s += ptr + ":";

            ptr = ptr.next;
        }

        return s;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        try {
            ll.add(0, 0);
            ll.add(0, 1);
            ll.add(0, 2);

            System.out.println(ll); // should be 2 1 0

            ll.add(1, 3);
            ll.add(2, 4);
            
            System.out.println(ll); // should be 2 3 4 1 0

            ll.add(5, 5);
            //ll.insert(-1, 5);
            //ll.insert(16, 5);
            System.out.println(ll);

            LinkedList[] lla = {new LinkedList(), new LinkedList()};

            for (int i = 0; i < 5; i++){
                lla[0].add(i, i);
                lla[1].add(i, i);
            }
            System.out.println("Equality: " + lla[0].equals(lla[1]));
            lla[1].remove(0);
            System.out.println("Equality: " + lla[0].equals(lla[1]));
            System.out.println("Equality: " + lla[0].equals(1));

            lla[0].clear();
            System.out.println(lla[0].size());
            System.out.println(lla[1].contains(3));
            System.out.println(lla[1].contains(33));
        } catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
    }
}
