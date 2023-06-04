package InClassPractice.Arrays;

public class Arrays {
    public static void main(String[] args) {
        int intarray[] = new int[3];

        for (int i : intarray){
            System.out.println(i);
        }

        int raggedArr[][] = new int[2][0];
        raggedArr[0] = new int[3];
        raggedArr[1] = new int[7];

        for (int i = 0; i < raggedArr.length; i++){
            for (int j = 0; j < raggedArr[i].length; j++){
                System.out.print(raggedArr[i][j] + " ");
            }
            System.out.println();
        }
        
        Object oarray[] = new Object[4];

        for (int i = 0; i < oarray.length; i++){
            oarray[i] = new Object(); // have to initialize Objects otherwise they're null
            System.out.println(oarray[i]);
        }

        Object[] oarr = {new AClass(), new AnotherClass(), new YetAnotherClass()};

        for (int i = 0; i < oarr.length; i++){
            // NOT THE BEST SOLUTION
            // if (oarr[i] instanceof AClass){
            //     AClass ac = (AClass) oarr[i];
            //     ac.function();
            // } else if (oarr[i] instanceof AnotherClass){
            //     AnotherClass ac = (AnotherClass) oarr[i];
            //     ac.function();
            // } else if (oarr[i] instanceof YetAnotherClass){
            //     YetAnotherClass ac = (YetAnotherClass) oarr[i];
            //     ac.function();
            // }

            // BETTER SOLUTION
            Interface ac = (Interface) oarr[i];
            ac.function();
        }

        Object[] primitives = new Object[8];

        //this works because of auto-boxing
        primitives[0] = (byte) 0;
        primitives[1] = (short) 0;
        primitives[2] = (int) 0;
        primitives[3] = (long) 0;
        primitives[4] = (float) 0;
        primitives[5] = (double) 0;
        primitives[6] = 'a';
        primitives[7] = true;

        for (int i = 0; i < primitives.length; i++){
            System.out.println(primitives[i].getClass().getName() + " " + primitives[i]);
        }

        // Auto unboxing from Object type to primitives
        int x = (Byte) primitives[0] + (Short) primitives[1];
        System.out.println(x);

    }
}
