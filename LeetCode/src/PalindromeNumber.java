/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/11/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        long reversed = 0;
        long orig = x;
        while (x > 0) {
            reversed = reversed * 10 + (x % 10);
            x /= 10;
        }

        return reversed == orig;
    }

    /**
     * another way. It only reverses half of the number and compare them.
     * note: in leetcode, "0" is palindrome number while "10" is not.
     */
    public boolean isPalindrome1(int x) {
        if (x == 0) return true;
        // in leetcode, negative numbers and numbers with ending zeros
        // are not palindrome
        if (x < 0 || x % 10 == 0)
            return false;

        // reverse half of the number
        // the exit condition is y >= x
        // so that overflow is avoided.
        int y = 0;
        while (y < x) {
            y = y * 10 + (x % 10);
            if (x == y)  // to check numbers with odd digits
                return true;
            x /= 10;
        }
        return x == y; // to check numbers with even digits
    }

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        System.out.println(pn.isPalindrome(10));
        System.out.println(pn.isPalindrome1(10));
    }
}
