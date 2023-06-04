package Serialization;

import java.io.File;

// -- For objects it is better to use Serialization. The Serializable interface
//    will cause an object to write (to a binary file): 
//		1) class name; 
//		2) class signature (field types);
//		3) instance variable values; 
//      4) class values (static variables) are NOT written to the file
//		5) transient instance variables are NOT written to the file
//    to the file
//    When deserializing the object constructor is NOT called. The object construction is
//    performed by the JVM based on the content of the serialized file. 
//    
//    Be careful when declaring final variables as their deserialized value is context dependent.
//    That is, the final value will be set by construction before the serialized value from the
//    file is read (if it is put into the file at all). Upon reading from the file, the final value
//    will not (cannot) be changed.
//

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTestHarness {

	public static void main(String[] args) {
		
		// -- construct and initialize an array of rectangles
		System.out.println("Construct 3 Rectangles");
		Rectangle r[] = { new Rectangle(), new Rectangle(10, 20, 30, 40), new Rectangle(50, 60, 70, 80)};

		// -- print the array contents
		System.out.println("Rectangle array");
		for (int i = 0; i < r.length; ++i) {
			System.out.println(i + ": " + r[i]);
		}

		// -- Serialize (write) the array to a binary/object file
		try {
			// -- wrap the target disc file in a raw byte FileOutputStream (object inside file)
			FileOutputStream fileOut = new FileOutputStream(new File("rectangleArray.ser"));
			
			// -- wrap the FileOutputStream in an ObjectOutputStream for serialization
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			// -- write the array of objects to the file
			out.writeObject(r);
			out.flush();
			
			// -- close the stream
			out.close();
			fileOut.close();
			
			System.out.println("Serialized data is saved in rectangleArray.ser");
		} catch (IOException i) {
			// -- in case the file cannot be opened
			System.out.println("can't open file");
		}

		// -- Deserialize the array from a binary/object file
		// -- create a reference to an array of Rectangle
		Rectangle rd[] = null;
		try {
			// -- set the static variable to show that it will not change on the read
			Rectangle.a = 0;
			// -- wrap the target disc file in a Stream (object inside file)
			FileInputStream fileIn = new FileInputStream(new File("rectangleArray.ser"));
			
			ObjectInputStream in = new ObjectInputStream(fileIn);
			// -- read the object from the file
			//    note that this returns an Object and therefore must be cast
			//    to the appropriate type to ensure usability
			Object ob = in.readObject();
			
			// -- close the stream
			in.close();
			fileIn.close();

			// -- verify the object class and cast (RTTI operation)
			if (ob instanceof Rectangle[]) {
				rd = (Rectangle[])ob;
			}
			else {
				System.out.println("Expected a Rectangle[], got a " + ob.getClass().getName());
				System.exit(0);
			}
		} catch (IOException i) {
			// -- in case the file cannot be opened
			System.out.println("can't open file");
			return;
		} catch (ClassNotFoundException c) {
			// -- in case the Rectangle.class file cannot be found after 
			//    reading the file
			System.out.println("Rectangle class not found");
			return;
		}
		System.out.println("=============================");
		System.out.println("Deserialized Rectangle array");
		for (int i = 0; i < rd.length; ++i) {
			System.out.println(i + ": " + rd[i]);
		}
	}
}
