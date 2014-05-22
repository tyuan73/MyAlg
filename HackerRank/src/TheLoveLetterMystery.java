/**
 * Created by yuantian on 5/19/14.
 */
/*
James got hold of a love letter that his friend Harry has written for his girlfriend. Being the prankster that James is, he decides to meddle with it. He changes all the words in the letter into palindromes. For any given string, he can only decrease the value of any one of the letters, for example, ‘d’ will become ‘c’, etc. This will count as one operation. (Also, he can decrease the value of letters only till he reaches ‘a’. ‘a’ cannot be further reduced to ‘z’) Find the minimum number of operations he needs to carry out to convert a given string into a palindrome.


Input Format
The first line contains an integer T i.e. number of test cases.
The next T lines will contain a string each.

Output Format
A single line containing number of minimum operations corresponding to each test case.

Constraints
1 ≤ T ≤ 10
1 ≤ length of string ≤ 104

Sample Input #00

3
abc
abcba
abcd
Sample Output #00

2
0
4
Explanation

For the first test case, abc -> abb -> aba.
For the second test case, abcba is a palindromic string.
For the third test case, abcd -> abcc -> abcb -> abca = abca -> abba.
 */

import java.util.*;

public class TheLoveLetterMystery {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.nextLine();
        while(t-- > 0) {
            String str = in.nextLine();
            int total = 0;
            for (int l = 0, r = str.length()-1; l < r; l++, r--) {
                total += Math.abs((str.charAt(l) - str.charAt(r)));
            }
            System.out.println(total);
        }
    }
}
