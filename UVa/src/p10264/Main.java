package p10264;

/**
 * Created by yuantian on 4/7/15.
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine());
            int total = 1 << n;
            int[] weight = new int[total];
            for (int i = 0; i < total; i++) {
                weight[i] = Integer.parseInt(in.nextLine());
            }

            int[] potency = new int[total];
            for (int i = 0; i < total; i++) {
                int bit = total >> 1;
                while (bit > 0) {
                    potency[i] += weight[bit ^ i];
                    bit >>= 1;
                }
            }

            int max = 0;
            for (int i = 0; i < total; i++) {
                int bit = total >> 1;
                while (bit > 0) {
                    max = Math.max(max, potency[i] + potency[i ^ bit]);
                    bit >>= 1;
                }
            }
            System.out.println(max);
        }
    }
}
