/**
 * Created by yuantian on 6/19/17.
 */
public class MinimumFactorization625 {
    public int smallestFactorization(int a) {
        if (a < 10) return a;
        long ans = 0, tens = 1;
        for (int i = 9; i >= 2; i--) {
            while ((a % i) == 0) {
                ans = tens * i + ans;
                tens *= 10;
                a /= i;
            }
        }
        if (a != 1 || ans > Integer.MAX_VALUE) return 0;
        return (int) ans;
    }
}
