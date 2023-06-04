package FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FileIOTestHarness {

	public static void main(String[] args) {
		
		// -- File class handles file and directory properties
		//    These are things you may want to process prior to 
		//    opening the file itself
		JFileChooser jfc = new JFileChooser();
		if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
			System.out.println("File descriptor information for " + jfc.getSelectedFile().getAbsolutePath());
			
			File file = new File(jfc.getSelectedFile().getAbsolutePath());
			
			System.out.println("Does it exist? " + file.exists());
			System.out.println("The file has " + file.length() + " bytes");
			System.out.println("Can it be read? " + file.canRead());
			System.out.println("Can it be written? " + file.canWrite());
			System.out.println("Is it a directory? " + file.isDirectory());
			System.out.println("Is it a file? " + file.isFile());
			System.out.println("Is the path absolute? " + file.isAbsolute());
			System.out.println("Is it hidden? " + file.isHidden());
			System.out.println("Absolute path is " + file.getAbsolutePath());
			System.out.println("Last modified on " + new java.util.Date(file.lastModified()));
		}

		// -- There are many classes to read/write files, every version of the SDK
		//    seems to add new ones
		
		// -- There are also various FilterStream classes that will do data conversion
		//    from byte/char to appropriate data type including Object types but data
		//    must be written/read in the same order using the same data types which
		//    places all of the burden on the programmer.
		
		// -- write ASCII characters to the file with a PrintWriter stream
		System.out.println("Writing ASCII character data to ints.ascii");
		File file = new File("./ints.ascii");
		System.out.println("Does it exist? " + file.exists());
		System.out.println("The file has " + file.length() + " bytes");
		System.out.println("Can it be read? " + file.canRead());
		System.out.println("Can it be written? " + file.canWrite());
		System.out.println("Is it a directory? " + file.isDirectory());
		System.out.println("Is it a file? " + file.isFile());
		System.out.println("Is the path absolute? " + file.isAbsolute());
		System.out.println("Is it hidden? " + file.isHidden());
		System.out.println("Absolute path is " + file.getAbsolutePath());
		System.out.println("Last modified on " + new java.util.Date(file.lastModified()));

		// -- human readable file output (ASCII format)
		PrintWriter outputwriter = null;		
		try {
			System.out.println("Absolute path is " + file.getAbsolutePath());
			outputwriter = new PrintWriter(file);
			for (int i = 0; i <= 10; ++i) {
				outputwriter.println(i);
			}
		} 
		catch (FileNotFoundException e1) {
			System.out.println("cannot open file");
		}
		finally {
			outputwriter.close();
		}

		// -- read the ASCII file with a Scanner 
	    System.out.println("Read ASCII character data from ints.ascii");
	    Scanner sc = null;
		try {	    	
	        sc = new Scanner(file);
	
//	        while (sc.hasNextLine()) {
//	            String s = sc.nextLine();
//	            System.out.println(s);
//	        }
	        while (sc.hasNextInt()) {
	            int s = sc.nextInt();
	            System.out.println(s);
	        }

	    } 
	    catch (java.io.FileNotFoundException e) {
	        e.printStackTrace();
	    }
		finally {
			sc.close();
		}
    
		// -- write binary data (raw bytes) to the file with a FileOutputStream stream
	    System.out.println("Write raw binary data to ints.binary");
		FileOutputStream output = null;
		file = new File("./ints.binary");
		try {
			System.out.println("Absolute path is " + file.getAbsolutePath());
			output = new FileOutputStream(file);
			// Output values to the file
			for (int i = -1; i <= 10; i++) {
				output.write(i);
			}
			output.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("cannot open file");
		} 
		catch (IOException e) {
			System.out.println("cannot perform write operation");
		}
		
		// -- read binary data (raw bytes) to the file with a FileInputStream stream
	    System.out.println("Read raw binary data from ints.binary");
		//file = new File("./ints.ascii");
		FileInputStream input;
		try {
			input = new FileInputStream(file);
			 // Read values from the file
			 float value;
//			 while ((value = input.read()) != -1) {
//				System.out.println(value);
//			}
			// -- the data will not be interpreted (parsed) in any way
			//    only raw bytes are returned
			while (input.available() > 0) {
				 value = input.read();
				System.out.println(value);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("cannot open file");
		} 
		catch (IOException e) {
			System.out.println("cannot perform write operation");
		}	
		
	    System.out.println("Read ASCII character data from TwoColumns.csv");
	    sc = null;
		try {	  
			file = new File("./TwoColumns.csv");
	        sc = new Scanner(file);
	
//	        while (sc.hasNextLine()) {
//	            String s = sc.nextLine();
//	            System.out.println(s);
//	        }
	        while (sc.hasNextDouble()) {
	            double s = sc.nextDouble();
	            System.out.println(s);
	        }

	    } 
	    catch (java.io.FileNotFoundException e) {
	        e.printStackTrace();
	    }
		finally {
			sc.close();
		}

	}

}