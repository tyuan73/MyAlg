package practice.sorting;

/**
 * Created by yuantian on 7/26/17.
 */

import java.util.*;

public class LilysHomework {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] input = new int[n][2];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            input[i][0] = in.nextInt();
            input[i][1] = i;
        }
        Arrays.sort(input, (a, b) -> (a[0] - b[0]));
        for (int i = 0; i < n; i++) {
            order[input[i][1]] = i;
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            while (order[i] != i) {
                total++;
                int target = order[i];
                order[i] = order[target];
                order[target] = target;
            }
        }
        Arrays.sort(input, (a, b) -> (b[0] - a[0]));
        for (int i = 0; i < n; i++) {
            order[input[i][1]] = i;
        }
        int total1 = 0;
        for (int i = 0; i < n; i++) {
            while (order[i] != i) {
                total1++;
                int target = order[i];
                order[i] = order[target];
                order[target] = target;
            }
        }
        System.out.println(Math.min(total, total1));
    }
}
