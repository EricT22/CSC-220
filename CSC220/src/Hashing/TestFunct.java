package Hashing;

public class TestFunct {
    public static int hash(String s){
        long sum = 0;
        for (int i = 0; i < s.length(); i++){
            sum += s.charAt(i);
        }

        return (int) (sum % 256); // keeps #s between 0 and 255
    }

    public static void main(String[] args) {
        System.out.println(hash("abc"));
        System.out.println(hash("cba")); // same as before... not effective b/c hash code is the same
    }
}
