package Serialization;

import java.io.Serializable;

// -- if the base class is not Serializable its fields 
//    will not be written
//
//    if the base class does not implement Serializable the
//    default constructor will be called with the derived
//    class is deserialized
public class Shape implements Serializable {

	// -- hash number representing this class
	private static final long serialVersionUID = -6263510995210834137L;

	private int shapeID = 0;
	
	private double x, y;
	
	protected Shape ()
	{
		this.x = 0;
		this.y = 0;
		shapeID = 10;
		System.out.println("Construction with Shape()");
	}

	protected Shape(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		shapeID = 20;
		System.out.println("Construction with Shape(double, double)");
	}
	
	@Override
	public String toString ()
	{
		return "(" + x + ", " + y + ") : " + shapeID;
	}
}
