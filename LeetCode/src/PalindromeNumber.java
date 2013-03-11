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
        while(x > 0) {
            reversed = reversed * 10 + (x%10);
            x /= 10;
        }

        return reversed == orig;
    }
}
