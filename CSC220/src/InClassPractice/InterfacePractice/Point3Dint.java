package InClassPractice.InterfacePractice;

public class Point3Dint implements Point3DInterface{

    private int x, y, z;

    public Point3Dint() {
    }

    public Point3Dint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3Dint(Point3Dint p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Point3Dint){
            Point3Dint p = (Point3Dint) o;
            return this.x == p.x && this.y == p.y && this.z == p.z; 
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setX(double x) {
        this.x = (int) x;
    }

    @Override
    public void setY(double y) {
        this.y = (int) y;
    }

    @Override
    public void setZ(double z) {
        this.z = (int) z;
    }

    @Override
    public Point3DInterface add(Point3DInterface a) {
        if (a instanceof Point3Dint){
            Point3Dint a3d = (Point3Dint) a;
            Point3Dint p = new Point3Dint(this.x + a3d.x, this.y + a3d.y, this.z + a3d.z);

            return p;
        }
        return null;
    }
}
