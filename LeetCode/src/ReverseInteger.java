/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseInteger {
    public int reverse(int x) {
        boolean isNeg = x < 0;
        if (isNeg)
            x = -x;
        int ret = 0;
        while (x > 0) {
            ret = ret * 10 + (x % 10);
            x /= 10;
        }
        return isNeg ? -ret : ret;
    }
}
