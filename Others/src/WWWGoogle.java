

/**
 * Make WWWDOT - GOOGLE = DOTCOM
 * Each character is a digit (0-9). Find all possible mappings that make the equation work.
 * The first character can not be 0.
 *
 * The anwser is:
 * 777589 - 188103 = 589486
 * 777589 - 188106 = 589483
 */

import java.util.*;

public class WWWGoogle {
    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('W', 0);
        map.put('D', 1);
        map.put('T', 2);
        map.put('G', 3);
        map.put('O', 4);
        map.put('L', 5);
        map.put('E', 6);
        map.put('C', 7);
        map.put('M', 8);
    }

    void run() {
        long start = System.currentTimeMillis();
        int[] digits = new int[10];
        for (int i = 0; i < 10; i++)
            digits[i] = i;

        perm(digits, 0);
        System.out.println(System.currentTimeMillis() - start);
    }

    void perm(int[] digits, int idx) {
        if (idx == digits.length) {
            validate(digits);
            return;
        }

        for (int i = idx; i < digits.length; i++) {
            swap(digits, i, idx);
            if (digits[map.get('W')] != 0 && digits[map.get('G')] != 0 && digits[map.get('D')] != 0) {
                perm(digits, idx + 1);
            }
            swap(digits, i, idx);
        }
    }

    void swap(int[] digits, int i, int j) {
        int t = digits[i];
        digits[i] = digits[j];
        digits[j] = t;
    }

    void validate(int[] digits) {
        //if (digits[map.get('W')] != 0 && digits[map.get('G')] != 0 && digits[map.get('D')] != 0) {
        int n1 = getNum(digits, "WWWDOT");
        int n2 = getNum(digits, "GOOGLE");
        int n3 = getNum(digits, "DOTCOM");
        if (n1 - n2 == n3) {
            System.out.printf("%d - %d = %d\n", n1, n2, n3);
        }
        //}
    }

    static int getNum(int[] digits, String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * 10 + digits[map.get(str.charAt(i))];
        }
        return num;
    }

    public static void main(String[] args) {
        WWWGoogle test = new WWWGoogle();
        test.run();
    }
}
