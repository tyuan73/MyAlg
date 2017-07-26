package weekly.week32;

/**
 * Created by yuantian on 5/18/17.
 */
public class GeometricTrick {
    public static void main(String[] args) {
        int n = 500001;
        long max = 0, val1 = 0, val2 = 0;
        for(int i = n; i > n-15000; i--) {
            for(int j = i-1; j>= 1; j--) {
                long prd = i * (long) j;
                long sq = (long) Math.sqrt(prd);
                if (prd == sq * sq) {
                    if (sq > max) {
                        max = sq;
                        val1 = i;
                        val2 = j;
                    }
                    break;
                }
            }
        }
        System.out.println(max);
        System.out.println(val1 + " * " + val2);
        /**
         * 499142
         * 499849 * 498436
         */
    }
}
