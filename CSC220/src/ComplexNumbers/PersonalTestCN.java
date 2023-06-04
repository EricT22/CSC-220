package ComplexNumbers;

public class PersonalTestCN {
    public static void main(String[] args) {
        ComplexNumber one = new ComplexNumber();
        System.out.println(one);
        
        ComplexNumber two = new ComplexNumber(2, 3);
        System.out.println(two);
        try{
            System.out.println(two.div(one));
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }finally {
            System.out.println("lol");
        }

        // one.setImag(-5);
        // one.setReal(4);

        // System.out.println(one.getReal() + "  " + one.getImag() + " toString: " + one);

        // ComplexNumber three = new ComplexNumber(two);
        // System.out.println(three);
        // System.out.println(two); 

        // System.out.println("two equals three: " + two.equals(three)); 
        // System.out.println("one equals three: " + one.equals(three));

        ComplexNumber x = new ComplexNumber(-25,0);

        System.out.println(x.sqrt());
    }
}
