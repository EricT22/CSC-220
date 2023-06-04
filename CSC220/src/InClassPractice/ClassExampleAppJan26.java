package InClassPractice;

public class ClassExampleAppJan26 {
    public static void main(String[] args) {
        System.out.println("Static var: " + ClassExampleJan26.staticFunction());

        //declare a ClassExample variable in stack memory
        
        ClassExampleJan26 ce;

        //instantiate a ClassExample object in heap memory
        ce = new ClassExampleJan26();

        //ce.e = 19; not good practice
        ClassExampleJan26.e = 19;

        //int a, b, c, d;

        // System.out.println(c); wont let you print anything because this variable hasn't been initialized
        System.out.println(ce.c);

        ClassExampleJan26 c0 = new ClassExampleJan26(1, 2, 3, 4);
        // --assingment is SHALLOW (reference only)
        ClassExampleJan26 c1 = c0;

        if (c0 == c1){
            System.out.println("c0 == c1");
        } else {
            System.out.println("c0 != c1");
        }

        c1 = new ClassExampleJan26(c0);

        System.out.println(c0);
        System.out.println(c1);
        
        if (c0.equals(c1)){
            System.out.println("c0 == c1");
        } else {
            System.out.println("c0 != c1");
        }

        c0.setX(42);
        System.out.println(c0);
        System.out.println(c1);

        String s = "hello world!";
        if (c0.equals(s)){
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }

        try {
            ClassExampleJan26.exceptionHandling(0);
            ClassExampleJan26.exceptionHandling(-1);
        } catch (ArithmeticException e){
            System.out.println(e);
        } catch (IllegalArgumentException e){
            System.out.println(e);
        } catch (Exception e){
            System.out.println(e);
        } finally { // always executes no matter what
            System.out.println("Done with try/catch/finally"); 
        }

        try {
            ce = new ClassExampleJan26(-5);
        } catch (IllegalArgumentException e){
            System.out.println(e);
            // System.out.println("terminating"); // disastrous error
            // System.exit(0);
            System.out.println("creating default ce"); // recoverable error
            ce = new ClassExampleJan26();
        }
    }

}
