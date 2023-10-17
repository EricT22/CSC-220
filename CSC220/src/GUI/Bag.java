package GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private static List<Character> bag = new ArrayList<Character>(7);

    public static void shuffle(){
        Collections.shuffle(bag);
    }

    public static Character getNext() {
        if (bagIsEmpty()){
            refillBag();
        }

        return bag.remove(0);
    }
    

    private static boolean bagIsEmpty() {
        return bag.isEmpty();
    }
    
    private static void refillBag() {
        bag.add('T');
        bag.add('L');
        bag.add('J');
        bag.add('O');
        bag.add('S');
        bag.add('Z');
        bag.add('I');

        shuffle();
    }

    private Bag(){
        // shouldn't instantiate Bag
    }
}