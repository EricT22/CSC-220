package LinkedListAssignment;

import RationalNumbers.RationalNumber;

public class RationalNumberLinkedList extends RationalNumberLLAbstract{

    public RationalNumberLinkedList(){ //works
        super();
    }

    @Override
    public String toString(){
        String str = "[";

        Node ptr = head.next;

        while (ptr != null) {
            if (ptr.next != null){
                str = str + ptr + ", ";
            } else {
                str = str + ptr;
            }
            ptr = ptr.next;
        }
        str = str + "]";

        return str;
    }

    @Override
    public int size() { //works
        int size = 0;

        Node ptr = head.next;

        while (ptr != null){
            size++;
            ptr = ptr.next;
        }
        return size;
    }

    @Override
    public boolean isEmpty() { //works
        if (head.next == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(RationalNumber e) { // works
        return indexOf(e) != -1;
    }

    @Override
    public RationalNumber[] toArray() { //works
        Node ptr = head.next;
        int size = size();
        RationalNumber[] arr = new RationalNumber[size];

        for (int i = 0; i < size; i++){
            arr[i] = ptr.data;
            ptr = ptr.next;
        }

        return arr;
    }

    @Override
    public boolean add(RationalNumber e) { // works
        Node ptr = head;

        while (ptr.next != null){
            ptr = ptr.next;
        }

        Node n  = new Node(e);
        ptr.next = n;
        return true;
    }

    @Override
    public void add(int index, RationalNumber e) throws ArrayIndexOutOfBoundsException{ //works
        if (index < 0){
            throw new ArrayIndexOutOfBoundsException("Index < 0");
        }

        Node ptr = head;

        try{
            for (int i = 0; i < index; i++){
                ptr = ptr.next;
            }

            Node n = new Node(e);

            n.next = ptr.next;
            ptr.next = n;
        } catch (NullPointerException ex){
            throw new ArrayIndexOutOfBoundsException("Index > size");
        }
    }

    @Override
    public boolean addAll(RationalNumber[] e) { //works
        for (int i = 0; i < e.length; i++){
            add(e[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, RationalNumber[] e) throws ArrayIndexOutOfBoundsException{ //works
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        for (int i = 0; i < e.length; i++){
            add(index + i, e[i]);
        }

        return true;
    }

    @Override
    public boolean remove(RationalNumber e) {  //works
        Node ptr = head;

        while (ptr.next != null){
            if (ptr.next.data.equals(e)){
                ptr.next = ptr.next.next;
                return true;
            }
            ptr = ptr.next;
        }

        return false;
    }

    @Override
    public RationalNumber remove(int index) throws ArrayIndexOutOfBoundsException{ //works
        if (index < 0){
            throw new ArrayIndexOutOfBoundsException("Index < 0");
        }

        try {
            Node ptr = head;

            for (int i = 0; i < index; i++){
                ptr = ptr.next;
            }
            RationalNumber returnNum = ptr.next.data;
            ptr.next = ptr.next.next;

            return returnNum;
        } catch (NullPointerException ex){
            throw new ArrayIndexOutOfBoundsException("Index > size");
        }
    }

    @Override
    public void clear() { //works
        head.next = null;
    }

    @Override
    public RationalNumber get(int index) throws ArrayIndexOutOfBoundsException { //works
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        Node ptr = head.next;
        for (int i = 0; i < index; i++){
            ptr = ptr.next;
        }

        return new RationalNumber(ptr.data);
    }

    @Override
    public RationalNumber set(int index, RationalNumber e) throws ArrayIndexOutOfBoundsException{ //works
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        Node ptr = head.next;

        for (int i = 0; i < index; i++){
            ptr = ptr.next;
        }

        RationalNumber returnData = new RationalNumber(ptr.data);
        ptr.data = e;

        return returnData;
    }

    @Override
    public int indexOf(RationalNumber e) { //works
        Node ptr = head.next;
        int ind = 0;

        while (ptr != null) {
            if (ptr.data.equals(e)){
                return ind;
            } else {
                ptr = ptr.next;
                ind++;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(RationalNumber e) { //works
        Node ptr = head.next;
        int lindo = -1;
        int curInd = 0;

        while (ptr != null){
            if (ptr.data.equals(e)){
                lindo = curInd;
            }
            ptr = ptr.next;
            curInd++;
        }

        return lindo;
    }

    @Override
    public boolean equals(Object e) {
        if (e instanceof RationalNumberLinkedList){
            RationalNumberLinkedList ll = (RationalNumberLinkedList) e;

            if (this.size() == ll.size()){
                Node thisPtr = head.next;
                Node llptr = ll.head.next;
                
                while (thisPtr != null){
                    if (!thisPtr.data.equals(llptr.data)){
                        return false;
                    }
                    thisPtr = thisPtr.next;
                    llptr = llptr.next;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public ListInterface subList(int fromindex, int toindex) throws IllegalArgumentException{ //works
        if (fromindex < 0 || toindex < 0 || fromindex >= size() || toindex >= size() || toindex < fromindex){
            throw new IllegalArgumentException("Index out of bounds.");
        }

        RationalNumber[] arr = new RationalNumber[toindex - fromindex + 1];

        Node ptr = head.next;

        for (int i = 0; i <= toindex; i++){
            if (i >= fromindex){
                arr[i - fromindex] = ptr.data;
            }
            ptr = ptr.next;
        }

        ListInterface li = new RationalNumberLinkedList();
        li.addAll(arr);
        return li;
    }
    
}
