/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class PowOfX {
    public double pow(double x, int n) {
        boolean isNag = n < 0;
        if (isNag)
            n = -n;
        double base = x;
        x = 1.0;
        while(n > 0) {
            if ((n&1) == 1)
                x *= base;
            n >>= 1;
            base *= base;
        }
        return isNag ? 1/x : x;
    }
}


