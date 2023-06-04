package InClassPractice.InterfacePractice;

public class Point3Dfloat implements Point3DInterface {
    
    private float x, y, z;

    public Point3Dfloat() {
    }

    public Point3Dfloat(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3Dfloat(Point3Dfloat p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    
    @Override
    public boolean equals(Object o){
        if (o instanceof Point3Dfloat){
            Point3Dfloat p = (Point3Dfloat) o;
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
        this.x = (float) x;
    }

    @Override
    public void setY(double y) {
        this.y = (float) y;
    }

    @Override
    public void setZ(double z) {
        this.z = (float) z;
    }

    @Override
    public Point3DInterface add(Point3DInterface a) {
        if (a instanceof Point3Dfloat){
            Point3Dfloat a3d = (Point3Dfloat) a;
            Point3Dfloat p = new Point3Dfloat(this.x + a3d.x, this.y + a3d.y, this.z + a3d.z);

            return p;
        }
        return null;
    }
}
