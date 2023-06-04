package InClassPractice.InterfacePractice;

public interface Point3DInterface {

    // -- getters return the associated member variables
	double getX();
	double getY();
	double getZ();
	
	// -- setters assign the associated member variables
	void setX(double x);
	void setY(double y);
	void setZ(double z);

	// -- performs this + a and returns a new values
	Point3DInterface add(Point3DInterface a);
}
