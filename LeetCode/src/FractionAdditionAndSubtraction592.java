/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/22/17
 * Time: 10:39 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class FractionAdditionAndSubtraction592 {
    public String fractionAddition(String expression) {
        String[] s = expression.split("/");
        long[] ans = {0, 1}, num = {0, 0};
        for (int i = 0; i < s.length - 1; i++) {
            num[0] = Long.parseLong(s[i]);
            int head = 1;
            if (s[i + 1].length() > 1 && s[i + 1].charAt(1) == '0') {
                head = 2;   // this is to take care of the case: "1/10+8/3"
            }

            num[1] = Long.parseLong(s[i + 1].substring(0, head));
            s[i + 1] = s[i + 1].substring(head);   // remove denominator from next string: "10+8" => "+8"

            ans[0] = ans[0] * num[1] + num[0] * ans[1];
            ans[1] *= num[1];
        }
        long gcd = gcd(ans[0], ans[1]);
        ans[0] /= gcd;
        ans[1] /= gcd;

        return ans[0] + "/" + ans[1];
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a < 0 ? -a : a;    // always return positive gcd
        return gcd(b, a % b);
    }

    /**
     * A even better solution. Use scanner.
     */
    public String fractionAddition1(String expression) {
        Scanner s = new Scanner(expression).useDelimiter("/|(?=[-+])");
        long num = 0, den = 1;
        while (s.hasNext()) {
            long a = s.nextLong(), b = s.nextLong();
            num = num * b + a * den;
            den *= b;
        }
        long gcd = gcd(num, den);
        return (num / gcd) + "/" + (den / gcd);
    }
}
