package ComplexNumbers;

public class TestComplexNumber {
    
	public static void main(String[] args) {

		ComplexNumber lhs = new ComplexNumber(1, 2);
		ComplexNumber rhs = new ComplexNumber(3, 4);
		
		ComplexNumber result;
		
		result = lhs.add(rhs);
		System.out.println("(" + lhs + ")" + " + (" + rhs + ") = " + result);
		
		result = lhs.sub(rhs);
		System.out.println("(" + lhs + ")" + " - (" + rhs + ") = " + result);

		result = lhs.mult(rhs);
		System.out.println("(" + lhs + ")" + " * (" + rhs + ") = " + result);
		
		result = lhs.div(rhs);
		System.out.println("(" + lhs + ")" + " / (" + rhs + ") = " + result);
		
		try {
			rhs = new ComplexNumber();
			result = lhs.div(rhs);
			System.out.println(lhs + " / " + rhs + " = " + result);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		
		System.out.println("|" + lhs + "| = " + lhs.mag());
		System.out.println("~(" + lhs + ") = " + lhs.conj());
		System.out.println("(" + lhs + ")^-2 = " + lhs.sqrt());
		lhs.setReal(-25);
		lhs.setImag(0);
		System.out.println("(" + lhs + ")^-2 = " + lhs.sqrt());
		System.out.println("(" + lhs + ") == (" + rhs + ") is " + lhs.equals(rhs));
		
		rhs.setReal(1);
		rhs.setImag(2);
		System.out.println("(" + lhs + ") == (" + rhs + ") is " + lhs.equals(rhs));

		System.out.println(lhs.getReal() + " " + lhs.getImag());
		
	}
}
