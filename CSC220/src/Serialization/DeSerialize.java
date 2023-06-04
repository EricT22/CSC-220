package Serialization;

/*
 * Change final variable in Rectangle to demonstrate
 * how final variables are initialized before construction
 * from the serialized file
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeSerialize {

	public static void main(String[] args) {
		// -- Deserialize the array from a binary/object file
		// -- create a reference to an array of Rectangle
		Rectangle rd[] = null;
		try {
			// -- set the static variable to show that it will not change on the read
			Rectangle.a = 1;
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

			// -- verify the object class and cast
			if (ob instanceof Rectangle[]) {
				rd = (Rectangle[])ob;
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

		System.out.println("Deserialized Rectangle array");
		for (int i = 0; i < rd.length; ++i) {
			System.out.println(i + ": " + rd[i]);
		}
	}

}
