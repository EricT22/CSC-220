package Serialization;

import java.io.Serializable;

// -- note that the base class needs to implement Serializable
//    if you want those fields to be written to the file
public class Rectangle extends Shape implements Serializable {

	// -- hash number representing this class
	private static final long serialVersionUID = -2241409914294902221L;

	// -- final variables are serialized to the file (as long as they are not transient or static)
	public final int ID = 25;
	
	// -- instance variables are serialized to the file
	private double width, height;
	
	// -- values of transient variables will not be serialized to the file
	private transient double x = 42, y = 37;
	
	// -- values of static variables will not be serialized to the file
	public static int a = 1;
	
	public Rectangle ()
	{
		super();
		this.width = 0;
		this.height = 0;
		this.x = 0;
		this.y = 0;
		
		System.out.println("Construction with Rectangle()");
	}
	
	public Rectangle (double x, double y, double width, double height)
	{
		super(x, y);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		System.out.println("Construction with Rectangle(double, double, double, double)");
	}
	
	@Override
	public String toString()
	{
		return "(base class) " + super.toString() + " : (derived class) " + width + ", " + height + " (transient) " + x + ", " + y + ":" + " (static) " + a + " (final) " + ID;
	}
}
