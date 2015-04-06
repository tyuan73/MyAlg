package p10057;

/**
 * Created by yuantian on 4/6/15.
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine());
            int[] input = new int[n];
            for (int i = 0; i < n; i++)
                input[i] = Integer.parseInt(in.nextLine());
            Arrays.sort(input);

            int mid = (n - 1) / 2;
            int ans = input[mid];
            if ((n & 1) == 1) {
                int count = 1;
                int x = mid - 1;
                while (x >= 0 && input[x] == ans) {
                    count++;
                    x--;
                }
                x = mid + 1;
                while (x < n && input[x] == ans) {
                    count++;
                    x++;
                }
                System.out.println(ans + " " + count + " 1");
            } else {
                int count = 1;
                int x = mid - 1;
                while (x >= 0 && input[x] == ans) {
                    count++;
                    x--;
                }
                x = mid + 1;
                while (x < n && input[x] == input[mid + 1]) {
                    count++;
                    x++;
                }
                System.out.println(ans + " " + count + " " + (input[mid + 1] - ans + 1));
            }
        }
    }
}
