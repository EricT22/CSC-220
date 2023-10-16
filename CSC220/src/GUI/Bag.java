package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private List<Character> bag;
    
    public Bag(){
        bag = new ArrayList<Character>(7);
    }

    public Character getNext() {
        if (bagIsEmpty()){
            refillBag();
        }

        return bag.remove(0);
    }
    

    private boolean bagIsEmpty() {
        return bag.isEmpty();
    }
    
    private void refillBag() {
        bag.add('T');
        bag.add('L');
        bag.add('J');
        bag.add('O');
        bag.add('S');
        bag.add('Z');
        bag.add('I');

        Collections.shuffle(bag);
    }
}