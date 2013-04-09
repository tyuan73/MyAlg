/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class PowOfX {
    public double pow(double x, int n) {
        boolean isNeg = n < 0 ? true : false;
        if (isNeg)
            n = -n;

        double ret = 1.0;
        while (n > 0) {
            if ((n & 1) > 0)
                ret *= x;
            x *= x;
            n >>= 1;
        }

        if (isNeg)
            return 1.0 / ret;
        return ret;
    }
}
