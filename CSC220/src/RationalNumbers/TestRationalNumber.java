package RationalNumbers;

public class TestRationalNumber {
	public static void main(String[] args) {
		RationalNumber rn0 = new RationalNumber(4, -6);
		System.out.println(rn0);
		RationalNumber rn1 = new RationalNumber(9, 15);
		System.out.println(rn1);

		RationalNumber result;
		
		result = rn0.add(rn1);
		System.out.println("(" + rn0 + ") + (" + rn1 + ") = " + result);
		result = rn0.sub(rn1);
		System.out.println("(" + rn0 + ") - (" + rn1 + ") = " + result);
		result = rn0.mult(rn1);
		System.out.println("(" + rn0 + ") * (" + rn1 + ") = " + result);
		result = rn0.div(rn1);
		System.out.println("(" + rn0 + ") / (" + rn1 + ") = " + result);
		result = rn0.div(rn0);
		System.out.println("(" + rn0 + ") / (" + rn1 + ") = " + result);
		
		result = new RationalNumber(10, 5);
		System.out.println(result.getNumerator() + "/" + result.getDenominator() + " = " + result);
		result = new RationalNumber(10, -5);
		System.out.println(result.getNumerator() + "/" + result.getDenominator() + " = " + result);
		
		rn0 = new RationalNumber(result);
		System.out.println(rn0.equals(result));
		rn0.setNumerator(0);
		System.out.println(rn0.equals(result));
		System.out.println(rn0);

		try {
			result.setDenominator(0);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		
		try {
			result = rn0.div(new RationalNumber());
			System.out.println("(" + rn0 + ") * (" + rn1 + ") = " + result);
		}
		catch (ArithmeticException e) {
			System.out.println(e);
		}

	}
}
