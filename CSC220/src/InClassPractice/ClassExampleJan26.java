package InClassPractice;

public class ClassExampleJan26 extends BaseClassB{

    // class variables are initialized
    // numeric types -> 0
    // char types -> 0
    // boolean types -> false
    // reference types -> null
    
    // --access modifiers
    int z; // package access
    private int b; // this class access only
    public int c = 5; // access from any class
    protected int d; // access from extending classes

    // -- static class var is always available at runtime
    public static int e; // shared across all ClassExample objects

    // --constructors
    public ClassExampleJan26(int a, int b, int c, int d){
        this.z = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public ClassExampleJan26(){
        this.z = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }

    public ClassExampleJan26(ClassExampleJan26 ce){
        this.z = ce.z;
        this.b = ce.b;
        this.c = ce.c;
        this.d = ce.d;
    }

    public ClassExampleJan26(double a) throws IllegalArgumentException{
        if (a < 0){
            throw new IllegalArgumentException("a < 0");
        }
    }

    @Override //compiler directive that checks to see if you've overridden a function in the inheritance tree if not throw an error
              //might have messed up the parameters, return type, method name, etc
    public String toString(){
        // --default
        // return getClass().getName() + '@' + Integer.toHexString(hashCode());
        return "ClassExample:" + super.toString() + "\n\tz = " + z + "\n\tb = " + b + "\n\tc = " + c + "\n\td = " + d + "\n\te = " + e;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof ClassExampleJan26){ 
            ClassExampleJan26 c = (ClassExampleJan26) o;
            return (this.z == c.z) && (this.b == c.b) && (this.c == c.c) && (this.d == c.d);
        }

        return false;
    }

    // message handler (public)
    public int publicSum(){
        return privateSum() + d;
    }

    // helper (private)
    private int privateSum(){
        return z + b + c;
    }

    // package accessible 
    int packageSum(){
        return z + b + d;
    }

    // inheritance accessible
    protected int protectedSum(){
        return c + d;
    }

    public static String staticFunction(){
        //static functions can ONLY access static member variables
        return "Static var = " + e;
    }

    public static void exceptionHandling(int a) throws IllegalArgumentException{
        if (a < 0){
            throw new IllegalArgumentException("a < 0");
        }
    }

    //getters and setters
    public int getX(){
        return z;
    }
    
    public int getB(){
        return b;
    }
    
    public int getC(){
        return c;
    }
    
    public int getD(){
        return d;
    }
    
    public void setX(int a){
        this.z = a;
    }    
    
    public void setB(int b){
        this.b = b;
    }    
    
    public void setC(int c){
        this.c = c;
    }
        
    public void setD(int d){
        this.d = d;
    }
}
