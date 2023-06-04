package Hashing;
/*
 * Rules for creating a "good" hash function
 * 
 * 1) The hash value is fully determined by the data being hashed. 
 * 2) The hash function uses all of the input data. 
 * 3) The hash function "uniformly" distributes the data across the entire set of possible hash values. 
 * 4) The hash function generates very different hash values for similar strings.
 * 
 * Note that the hash function is not invertible, you cannot get the original string back from the hash value.
 * High performance hash functions
 * Message Digest 5 (MD5) -- used for checksum and data integrity -- https://en.wikipedia.org/wiki/MD5
 * Secure Hash Algorithm 2 (SHA-2) -- used in cryptography applications --  https://en.wikipedia.org/wiki/SHA-2
 * 
 * 
 */
import java.util.Random;
import java.util.Scanner;

public class HashFunctions {

	// -- the range of the hash function
	//    (a prime number typically larger than the number of items we want
	//     to hash, ideally > 2 * number of values)
	private static final int HASHRANGE = 257;
	
	// -- note that long strings will cause hash and hash0 to produce different results
	public static int hash (String key) {
		long poly = key.charAt(0);
		// -- 31, 33, 37, 39, 41
		long b = 31; 

		// -- polynomial evaluation by Horner's method
		for (int i = 1; i < key.length(); ++i) {
			char c = key.charAt(i);
			poly *= b;
			poly += c;
		}
		// -- can wrap to negative so always take the absolute value
		return (int)Math.abs((poly % HASHRANGE));
	}
	
	// -- same as above but may cause different results dependent
	//    on the size of the intermediate numbers
	public static int hash0 (String key) {
		long poly = 0;
		// -- 31, 33, 37, 39, 41
		long b = 31; 
		
		// -- polynomial evaluation by direct method
		for (int i = 0; i < key.length(); ++i) {
			char c = key.charAt(i);
			poly += c * (long)Math.pow(b, key.length() - i - 1);
		}
		// -- can wrap to negative so always take the absolute value
		return (int)Math.abs((poly % HASHRANGE));			
	}
	
	
	public static int hash1 (String key) {
		long accum1 = 0;
		
		int exp = key.length();
		
		for (int i = 0; i < exp; ++i) {
			int c = key.charAt(i);
			// -- the modulo function is used here to ensure
			//    that the numbers do not get large
			//    if your function does not produce large numbers
			//    (e.g. the key string is 4 characters) then the
			//    modulo can be computed at the end of the summation
			accum1 = (int)((accum1 + c * (long)(Math.pow(c,  i)))) % HASHRANGE;
		}
		
		return((int)Math.abs(accum1));
	}
	
	public static int hash2(String key) { 
		int sum = 0; 

		// -- Sum up all the characters in the string 
		for (int i = 0; i < key.length(); ++i) {
			sum += key.charAt(i);			
		}
		// Return the sum mod the table size 
		return sum % HASHRANGE;
	}

	
	
	public static int hash3(String key) {
		// -- make sure to seed the random number generator
		//    to ensure the same random sequence for each
		//    hash
		Random rn = new Random(1);

		int sum = 0;
		for (int i = 0; i < key.length(); ++i) {
			sum += key.charAt(i) * (rn.nextInt(1 << 20) % HASHRANGE);
		}
		return sum % HASHRANGE;
	}
	
	public static int hash4(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); ++i) {
			hash = hash ^ key.charAt(i); 
		}
		return (hash % HASHRANGE);
	
	}
	
	// -- H(s) = (s0*a0 + s1*a1 + s2*a2 + s3*a3) % N 
	//    where: N is the size of the list, a prime number
	//           a0-a3 are random numbers 0..N (random number % N)
	//    uses only the first 4 characters of the key
	public static int hash5(String key) {
		Random rn = new Random(1);

		int a[] = {rn.nextInt() % HASHRANGE, rn.nextInt() % HASHRANGE, rn.nextInt() % HASHRANGE, rn.nextInt() % HASHRANGE};

		int sum = 0;
		for (int i = 0; i < Math.min(key.length(), a.length); ++i) {
			sum += a[i] * key.charAt(i);
		}
		
		return sum % HASHRANGE;
	}

	// -- ElfHash, Peter Weinberger
	public static int PJW(String key) {
		int  h = 0, high;
		for (int i = 0; i < key.length(); ++i) {
			h = ( h << 4 ) + key.charAt(i);
		    if ((high = h & 0xF0000000) != 0) {
		        h ^= high >> 24;
		    }
		    h &= ~high;
		}
		return (int)(h % HASHRANGE);
	}

	
	public static void main(String[] args) {
		System.out.print("key: ");
		Scanner kb = new Scanner(System.in);
		String s = kb.nextLine(); //.toLowerCase();
		System.out.println(hash(s));
		System.out.println(hash0(s));
		System.out.println(hash1(s));
		System.out.println(hash2(s));
		System.out.println(hash3(s));
		System.out.println(PJW(s));
		System.out.println(hash4(s));
		System.out.println(hash5(s));

        kb.close();
	}

}
