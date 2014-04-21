/**
 * Created by yuantian on 4/21/14.
 */

import java.util.*;

public class ServiceLane {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int t = in.nextInt();
        int[] seg = new int[n];
        int[] a2 = new int[n];
        int[] a3 = new int[n];

        for (int i = 0; i < n; i++) {
            seg[i] = in.nextInt();
        }

        a2[0] = seg[0] >= 2 ? 0 : Integer.MAX_VALUE;
        a3[0] = seg[0] == 3 ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            switch (seg[i]) {
                case 3:
                    a2[i] = Math.min(a2[i-1], i);
                    a3[i] = Math.min(a3[i-1], i);
                    break;
                case 2:
                    a2[i] = Math.min(a2[i-1], i);
                    a3[i] = Integer.MAX_VALUE;
                    break;
                case 1:
                    a2[i] = Integer.MAX_VALUE;
                    a3[i] = Integer.MAX_VALUE;
                    break;
                default:
                    break;
            }
        }

        while(t-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            if (a3[r] <= l) {
                System.out.println(3);
            } else if (a2[r] <= l) {
                System.out.println(2);
            } else {
                System.out.println(1);
            }
        }
    }
}
