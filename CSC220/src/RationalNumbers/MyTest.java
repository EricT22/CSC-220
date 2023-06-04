package RationalNumbers;

public class MyTest {
    public static void main(String[] args) {
        // RationalNumber r = new RationalNumber(6,-10);
        // RationalNumber r2 = new RationalNumber(2, -4);
        
        try {
            RationalNumber r4 = new RationalNumber(0,0);
            System.out.println(r4);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        
        // RationalNumber r3 = new RationalNumber(r.sub(r2));

        // System.out.println(r3);
        // System.out.println(r3.equals(0));

        // System.out.println(r2.getNumerator());

        // try {
        //     r.setNumerator(0);
        //     System.out.println(r.getNumerator());
        // } catch (IllegalArgumentException e) {
        //     System.out.println(e);
        // }

        // try {
        //     System.out.println(r2.div(r));
        // } catch (ArithmeticException e) {
        //     System.out.println(e);
        // }
    }

}
