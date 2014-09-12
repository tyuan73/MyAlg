/**
 * Created by yuantian on 9/12/14.
 */
public class SingleNumber {
    public int singleNumber(int[] A) {
        int res = 0;
        for(int x : A)
            res ^= x;
        return res;
    }
}
