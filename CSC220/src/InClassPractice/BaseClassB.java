package InClassPractice;

public class BaseClassB extends BaseClassA{ // Base class b is a base class a
    protected char b = 'b';

    public char getA(){
        return a;
    }

    @Override
    public String toString(){
        return super.toString() + "\n\tb = " + b;
    }
}
