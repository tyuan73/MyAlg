package projecteuler1;

/**
 * Created by yuantian on 7/15/14.
 */

import java.util.*;

public class SpecialPythagoreanTriplet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] d = new int[3001];
        Arrays.fill(d, -1);
        for(int i = 1; i < 3000; i++) {
            for(int j = i+1; j < 3000; j++) {
                int diff = j*j - i*i;
                int c = (int) Math.round(Math.sqrt(diff));
                int sum = i+j+c;
                if (sum > 3000)
                    break;
                if (diff == c*c) {
                    d[sum] = Math.max(d[sum], i*j*c);
                }
            }
        }

        int t = in.nextInt();
        while(t-- > 0) {
            System.out.println(d[in.nextInt()]);
        }
    }
}
