package InClassPractice.AbstractClassExample;

public class UseAbstractClass2 extends AbstractClass{

    public int y;
    
    public UseAbstractClass2(){
        System.out.println("Default UseAbstractClass2 constructor");
    }

    public UseAbstractClass2(int y){
        super(y);
        System.out.println("Overloaded UseAbstractClass2 constructor");
        this.y = y;
    }

    @Override
    public double piSquared() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
