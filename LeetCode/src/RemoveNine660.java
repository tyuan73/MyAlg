/*

*/

import java.util.*;
import java.io.*;

public class RemoveNine660 {
    /**
     * Base-10 to Base-9
     */
    public int newInteger(int n) {
        int ans = 0;
        int base = 1;
        while (n > 0) {
            ans += (n % 9) * base;
            n /= 9;
            base *= 10;
        }
        return ans;
    }

    /**
     * One line
     */
    public int newInteger1(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }

    /**
     * if remove 6 or other numbers.
     */
    public int remove6(int n) {
        String str = Integer.toString(n, 9);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '6')
                sb.append((char) (str.charAt(i) + 1));
            else
                sb.append(str.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    }

    static public void main(String[] args) {
        RemoveNine660 test = new RemoveNine660();
        int num = 1;
        for (int i = 0; i < 10000000; i++) {
            while (!valid(num)) num++;
            if (test.remove6(i + 1) != num) {
                System.out.println("wrong!");
            }
            num++;
        }
        System.out.println("done!");
    }

    /**
     * Brute force to check if a number has digit '6' - for test
     */
    static boolean valid(int n) {
        while (n > 0) {
            if ((n % 10) == 6) return false;
            n /= 10;
        }
        return true;
    }
}