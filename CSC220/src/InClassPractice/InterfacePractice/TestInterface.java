package InClassPractice.InterfacePractice;

public class TestInterface {
    public static void main(String[] args) {
        Point3DInterface p3di[] = new Point3DInterface[3];

        p3di[0] = new Point3Ddouble(1, 1, 1);
        p3di[1] = new Point3Dfloat(2, 2, 2);
        p3di[2] = new Point3Dint(3, 3, 3);

        for (Point3DInterface p : p3di){
            System.out.println(p);
        }

        Point3DInterface sum = p3di[0].add(p3di[1]);
        System.out.println(sum);
    }
}
