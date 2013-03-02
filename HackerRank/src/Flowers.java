/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/21/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class Flowers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] prices = new int[n];
        for(int i = 0; i < n; i++) {
            prices[i] = in.nextInt();
        }

        Arrays.sort(prices);
        int m = 1;
        int ret = 0;
        for(int i = n-1, j = 0; i >= 0; i--, j++) {
            if(j == k) {j = 0; m++;}
            ret += prices[i]*m;
        }
        System.out.println(ret);
    }
}
