/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/11/13
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        char[] ret = new char[n + m];

        int index = n + m - 1;
        Arrays.fill(ret, '0');
        int c = 0;
        for (int i1 = n - 1; i1 >= 0; i1--) {
            c = 0;
            int x1 = num1.charAt(i1) - '0';
            for (int i2 = m - 1; i2 >= 0; i2--) {
                int x2 = num2.charAt(i2) - '0';
                int x = x1 * x2 + c + (ret[i1 + i2 + 1] - '0');
                ret[i1 + i2 + 1] = (char) ('0' + (x % 10));
                c = x / 10;
            }
            ret[i1] = (char) (c + '0');
        }

        String res = new String(ret);
        index = 0;
        while (index < n + m - 1 && ret[index] == '0')
            index++;
        return res.substring(index);
    }
}
