package InClassPractice.AbstractClassExample;

public class TestAbstractClass {
    public static void main(String[] args) {
        //AbstractClass ac = new AbstractClass(); //not allowed
        System.out.println(AbstractClass.pi);

        AbstractClass ac = new UseAbstractClass();
        ac = new UseAbstractClass2();
        

        System.out.println(ac.getX());
    }    
}
