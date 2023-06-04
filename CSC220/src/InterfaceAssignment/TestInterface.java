package InterfaceAssignment;

public class TestInterface {
    public static void main(String[] args) {
		
		// -- declare references to the interface
		AddInterface ai0;
		AddInterface ai1;
		AddInterface ai2;

		// -- instantiate interface references with String objects
		ai0 = new AddInterfaceString("Hello, ");
		ai1 = new AddInterfaceString("World!");
		ai2 = new AddInterfaceString(ai0);
		
		System.out.println(ai0);
		System.out.println(ai1);
		System.out.println(ai0.add(ai1));
		System.out.println(ai0.add(ai2).add(ai1));

		// -- instantiate interface references with Integer objects
		// -- instantiate interface references with String objects
		ai0 = new AddInterfaceInteger(1);
		ai1 = new AddInterfaceInteger(2);
		ai2 = new AddInterfaceInteger(ai0);
		
		System.out.println(ai0);
		System.out.println(ai1);
		System.out.println(ai0.add(ai1));
		System.out.println(ai0.add(ai2).add(ai1));

	}
}
