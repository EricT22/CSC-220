package InClassPractice.InterfacePractice;

public class Point3Ddouble implements Point3DInterface{
    
    private double x, y, z;

    public Point3Ddouble() {
    }

    public Point3Ddouble(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3Ddouble(Point3Ddouble p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Point3Ddouble){
            Point3Ddouble p = (Point3Ddouble) o;
            return Math.abs(this.x - p.x) < 0.000001 && 
                   Math.abs(this.y - p.y) < 0.000001 && 
                   Math.abs(this.z - p.z) < 0.000001; 
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
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public Point3DInterface add(Point3DInterface a) {
        if (a instanceof Point3Ddouble){
            Point3Ddouble a3d = (Point3Ddouble) a;
            Point3Ddouble p = new Point3Ddouble(this.x + a3d.x, this.y + a3d.y, this.z + a3d.z);

            return p;
        } else if (a instanceof Point3Dfloat){
            Point3Dfloat a3d = (Point3Dfloat) a;
            Point3Ddouble p = new Point3Ddouble(this.x + a3d.getX(), this.y + a3d.getY(), this.z + a3d.getZ());

            return p;
        } else if (a instanceof Point3Dint){
            Point3Dint a3d = (Point3Dint) a;
            Point3Ddouble p = new Point3Ddouble(this.x + a3d.getX(), this.y + a3d.getY(), this.z + a3d.getZ());

            return p;
        }
        return null;
    }
}
