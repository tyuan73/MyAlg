/*

*/

import java.util.*;
import java.io.*;

public class TwentyFourGame679 {
    public boolean judgePoint24(int[] nums) {
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) continue;
                    for (int q = 0; q < 4; q++) {
                        if (q == i || q == j || q == k) continue;
                        double a = nums[i], b = nums[j], c = nums[k], d = nums[q];
                        if (check3(a + b, c, d) || check3(a - b, c, d) || check3(a * b, c, d) || check3(a / b, c, d) || check3(b / a, c, d))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean check3(double a, double b, double c) {
        if (check2(a + b, c) || check2(a - b, c) || check2(a * b, c) || (a != 0 && check2(b / a, c)))
            return true;
        if (check2(a + c, b) || check2(a - c, b) || check2(a * c, b) || (a != 0 && check2(c / a, b)))
            return true;
        if (check2(b + c, a) || check2(b - c, a) || check2(b * c, a) || (c != 0 && check2(b / c, a)))
            return true;
        return false;
    }

    private boolean check2(double a, double b) {
        if (is24(a + b) || is24(a - b) || is24(a * b) || (a != 0 && is24(b / a)))
            return true;
        return false;
    }

    private boolean is24(double a) {
        if (Math.abs(Math.abs(a) - 24) <= 0.01 || Math.abs(Math.abs(1 / a) - 24) <= 0.01)
            return true;
        return false;
    }

    /**
     * Test: out of the total 495 possible combination, 404 are valid.
     */
    static public void main(String[] args) {
        TwentyFourGame679 test = new TwentyFourGame679();
        int[] data = new int[4];
        int count = 0, total = 0;
        for (int i = 1; i < 10; i++) {
            data[0] = i;
            for (int j = i; j < 10; j++) {
                data[1] = j;
                for (int k = j; k < 10; k++) {
                    data[2] = k;
                    for (int q = k; q < 10; q++) {
                        data[3] = q;
                        total++;
                        if (test.judgePoint24(data))
                            count++;
                    }
                }

            }
        }
        System.out.println(count + " / " + total);

        int[] data2 = {3, 3, 8, 8};
        System.out.println(test.judgePoint24(data2));
    }
}