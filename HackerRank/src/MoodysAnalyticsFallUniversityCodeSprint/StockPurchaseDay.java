package MoodysAnalyticsFallUniversityCodeSprint;

import java.util.*;

public class StockPurchaseDay {
    /**
     * Solution without binary search
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }

        int q = in.nextInt();
        int[][] Q = new int[q][2];
        for (int i = 0; i < q; i++) {
            Q[i][0] = in.nextInt();
            Q[i][1] = i;
        }
        Arrays.sort(Q, (a, b) -> (b[0] - a[0]));
        int idx = n - 1;
        int[] output = new int[q];
        for (int[] x : Q) {
            while (idx >= 0 && A[idx] > x[0]) idx--;
            output[x[1]] = idx == -1 ? -1 : idx + 1;
        }

        for (int x : output) {
            System.out.println(x);
        }
        in.close();
    }

    /**
     * Solution with binary search
     */
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
        }
        int[] min = new int[n];
        min[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--)
            if (A[i] < min[i + 1])
                min[i] = A[i];
            else
                min[i] = min[i + 1];

        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            int idx = bSearch(min, x);
            if (idx == -1)
                System.out.println(-1);
            else
                System.out.println(idx + 1);
        }
        in.close();
    }

    static int bSearch(int[] min, int x) {
        int l = -1, r = min.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (min[mid] <= x)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }
}
