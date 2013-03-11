/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class StockMaximize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] stock = new int[n];
            for(int i = 0; i < n; i++)
                stock[i] = in.nextInt();
            int[] max = new int[n];
            max[n-1] = stock[n-1];
            for(int i = n-2; i >= 0; i--) {
                max[i] = Math.max(stock[i], max[i+1]);
            }
            long total = 0;
            for(int i = 0; i < n; i++)
                total = total + max[i] - stock[i];
            System.out.println(total);
        }
    }
}
