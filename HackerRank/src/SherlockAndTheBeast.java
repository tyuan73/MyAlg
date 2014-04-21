/**
 * Created by yuantian on 4/21/14.
 */

import java.util.*;

public class SherlockAndTheBeast {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while(t-- > 0) {
            int n = in.nextInt();
            int a = 0, b = 0;
            for(; a <= n; a += 5) {
                if ((n - a) % 3 == 0) {
                    b = (n - a);
                    break;
                }
            }

            if (a > n) {
                System.out.println(-1);
            } else {
                char[] output = new char[n];
                Arrays.fill(output, '3');
                for (int i = 0; i < b; i++) {
                    output[i] = '5';
                }
                System.out.println(output);
            }
        }
    }
}
