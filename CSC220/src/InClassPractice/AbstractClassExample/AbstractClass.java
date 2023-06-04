package InClassPractice.AbstractClassExample;

public abstract class AbstractClass implements Interface{
    
    private int x;

    public static final double pi = 3.1415926535;

    public AbstractClass(){
        System.out.println("Default abstract Constructor");
        x = 0;
    }

    public AbstractClass(int x){
        System.out.println("Overriden abstract Constructor");
        this.x = x;
    }

    public abstract double piSquared();

    @Override 
    public void interfaceFunction(){
        
    }

    public int getX(){
        return x;
    }

}
