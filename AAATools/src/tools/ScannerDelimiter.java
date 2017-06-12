package tools;

/**
 * Created by yuantian on 6/12/17.
 */

import java.util.*;

public class ScannerDelimiter {
    public static void main(String[] args) {
        String str = "L1e2t1C1o1d1e1";

        // with (?=....) scanner keeps the delimiters
        Scanner in = new Scanner(str).useDelimiter("(?=[a-zA-Z])");
        while (in.hasNext()) {
            System.out.println(in.next());
        }
        /**
         * output:
         *
         * L1
         * e2
         * t1
         * C1
         * o1
         * d1
         * e1
         *
         */

        // another example (https://leetcode.com/problems/fraction-addition-and-subtraction/#/description)
        String input = "-1/2+1/-2+1/3";
        // user "/", "+", "-" as delimiter, but only keep "-"
        Scanner in1 = new Scanner(input).useDelimiter("/|\\+|(?=-)");
        while (in1.hasNext()) {
            System.out.println(in1.next());
        }
        /**
         * output:
         *
         *  -1
         *  2
         *  1
         *  -2
         *  1
         *  3
         *
         */
    }
}
