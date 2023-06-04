package InClassPractice.AbstractClassExample;

public class UseAbstractClass extends AbstractClass {

    public int y;

    public UseAbstractClass(){
        System.out.println("Default UseAbstractClass constructor");
    }

    public UseAbstractClass(int y){
        super(y);
        System.out.println("Overloaded UseAbstractClass constructor");
        this.y = y;
    }

    @Override
    public double piSquared() {
        return Math.pow(pi, 2);
    }
    
}
