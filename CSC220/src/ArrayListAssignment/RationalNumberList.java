package ArrayListAssignment;

import RationalNumbers.RationalNumber;

public class RationalNumberList extends RationalNumberAbstract {

    public RationalNumberList(){
        super();
    }

    public RationalNumberList(int capacity){
        super(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(RationalNumber e) {
        for (int i = 0; i < size; i++){
            if (e.equals(data[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public RationalNumber[] toArray() {
        return copyOfRange(data, 0, size - 1);
    }

    @Override
    public boolean add(RationalNumber e) {
        if (size == capacity){
            data = copyOf(data, ++capacity);
        }

        data[size] = e;
        size++;
        
        return true;
    }

    @Override
    public void add(int index, RationalNumber e) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }

        if (size == capacity){
            data = copyOf(data, ++capacity);
        }

        for (int i = size; i >= index; i--){
            if (i == index){
                data[i] = e;
            } else {
                data[i] = data[i - 1];
            }
        }
        size++;
    }

    @Override
    public boolean addAll(RationalNumber[] e) {
        capacity = (capacity < e.length + size) ? e.length + size : capacity;
        data = copyOf(data, capacity);

        for (int i = 0; i < e.length; i++){
            add(e[i]);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, RationalNumber[] e) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }

        capacity = (capacity < e.length + size) ? e.length + size : capacity;
        data = copyOf(data, capacity);

        for (int i = 0; i < e.length; i++){
            add(index + i, e[i]);
        }

        return true;
    }

    @Override
    public boolean remove(RationalNumber e) {
        int index = indexOf(e);

        if (index == -1){
            return false;
        } else if (index == size - 1){
            data[index] = null;
        } else {
            for (int i = index; i < size - 1; i++){
                data[i] = data[i + 1];
            }
        }
        size--;
        return true;
    }

    @Override
    public RationalNumber remove(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }

        RationalNumber removedNum = data[index];

        if (index == size - 1){
            data[index] = null;
        } else {
            for (int i = index; i < size - 1; i++){
                data[i] = data[i + 1];
            }
        }
        size--;
        return removedNum;
    }

    @Override
    public void clear() {
        capacity = 10;
        data = new RationalNumber[capacity];
        size = 0;
    }

    @Override
    public RationalNumber get(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }
        return data[index];
    }

    @Override
    public RationalNumber set(int index, RationalNumber e) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > size - 1){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds.");
        }

        RationalNumber replacedNum = data[index];
        data[index] = e;
        return replacedNum;
    }
    
    @Override
    public int indexOf(RationalNumber e) {
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(RationalNumber e) {
        for (int i = size - 1; i > 0; i--){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int n) {
        if (capacity < n){
            capacity = n;
            data = copyOf(data, capacity);
        }
    }

    @Override
    public boolean equals(Object e) {
        if (e instanceof RationalNumberList){
            RationalNumberList rnl = (RationalNumberList) e;
            
            if (rnl.size == size){
                for (int i = 0; i < size; i++){
                    if (!data[i].equals(rnl.data[i])){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public ListInterface subList(int fromindex, int toindex) throws ArrayIndexOutOfBoundsException{
        if (fromindex < 0 || toindex < 0 || fromindex >= size || toindex >= size || toindex < fromindex){
            throw new ArrayIndexOutOfBoundsException("Illegal argument(s)");
        }

        RationalNumberList sl = new RationalNumberList();
        sl.ensureCapacity(toindex - fromindex + 1);
        sl.addAll(copyOfRange(data, fromindex, toindex));

        return sl;
    }

    private RationalNumber[] copyOf(RationalNumber[] darr, int cap) {
        RationalNumber[] returnArr = new RationalNumber[cap];

        for (int i = 0; i < this.size; i++){
            returnArr[i] = darr[i];
        }
        return returnArr;
    }

    // begin and end are both inclusive
    private RationalNumber[] copyOfRange(RationalNumber[] dArr, int begin, int end){
        RationalNumber[] returnArr = new RationalNumber[end - begin + 1];

        for (int i = begin; i <= end; i++){
            returnArr[i - begin] = dArr[i];
        }
        return returnArr;
    }
    
}
