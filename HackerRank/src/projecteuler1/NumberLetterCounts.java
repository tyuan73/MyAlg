package projecteuler1;

/**
 * Created by yuantian on 7/29/14.
 */

/*
The numbers 1 to 5 written out in words are One, Two, Three, Four, Five

First character of each word will be capital letter for example:
104382426112 is One Hundred Four Billion Three Hundred Eighty Two Million Four Hundred Twenty Six Thousand One Hundred Twelve

Given a number, you have to write it in words.

Input Format
The first line contains an integer T , i.e., number of test cases.
Next T lines will contain an integer N.

Output Format
Print the values corresponding to each test case.

Constraints
1≤T≤10
0≤N≤1012

Sample Input

2
10
17
Sample Output

Ten
Seventeen
 */

import java.util.*;

public class NumberLetterCounts {
    static long TRI = 1000000000000L;
    static long BIL = 1000000000;
    static long MIL = 1000000;
    static long THO = 1000;
    static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String[] s = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while (t-- > 0) {
            long n = in.nextLong();
            if (n == 0)
                System.out.println("Zero");
            else
                System.out.println(toStr(n));
        }
    }

    static String toStr(long n) {
        if (n >= TRI) {
            return (toStr(n / TRI) + " Trillion " + toStr(n % TRI)).trim();
        } else if (n >= BIL) {
            return (toStr(n / BIL) + " Billion " + toStr(n % BIL)).trim();
        } else if (n >= MIL) {
            return (toStr(n / MIL) + " Million " + toStr(n % MIL)).trim();
        } else if (n >= THO) {
            return (toStr(n / THO) + " Thousand " + toStr(n % THO)).trim();
        } else if (n >= 100) {
            return (toStr(n / 100) + " Hundred " + toStr(n % 100)).trim();
        } else if (n >= 20) {
            return (tens[(int) n / 10] + " " + toStr(n % 10)).trim();
        } else {
            return s[(int) n];
        }
    }
}
