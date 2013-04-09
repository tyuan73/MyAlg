/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 0)
            return digits;
        int[] ret = new int[n + 1];
        ret[0] = carry;
        for (int i = 1; i <= n; i++)
            ret[i] = digits[i - 1];
        return ret;
    }
}
