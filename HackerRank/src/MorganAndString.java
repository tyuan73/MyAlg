/**
 * Created by yuantian on 4/29/14.
 */
/*
Jack and Daniel are friends. Both of them like letters, especially upper-case ones.
Friends are cutting upper-case letters from newspapers and each one of them has their collection of letters, both stored in separate stack.
One beautiful day Morgan visited Jack and Daniel. He saw their collections. Morgan wondered what is the lexicographically minimal string, made of that two collections. He can take a letter from a collection when it is on the top of the stack.
Also Morgan wants to use all the letters in boys’ collections.
Help Morgan to solve this task.

Input Format
The first line contains number of testcases T.
Every next two lines have such format:
The first line contains string A.
The second line contains string B.

Output Format
Lexicographically minimal string S for each test in new line.

Constraints
1 ≤ T ≤ 5
1 ≤ |A| ≤ 105
1 ≤ |B| ≤ 105
A and B contain upper-case letters only.

Sample Input

2
JACK
DANIEL
ABACABA
ABACABA
Sample Output

DAJACKNIEL
AABABACABACABA
 */

import java.util.*;

public class MorganAndString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.nextLine();

        while(t-- > 0) {
            String A = in.nextLine();
            String B = in.nextLine();

            int i = 0, j = 0;
            StringBuffer sb = new StringBuffer();

            while(i < A.length() && j < B.length()) {
                if (A.charAt(i) < B.charAt(j)) {
                    sb.append(A.charAt(i++));
                } else if (A.charAt(i) > B.charAt(j)) {
                    sb.append(B.charAt(j++));
                } else {
                    int x = i, y = j;
                    char a = A.charAt(i);
                    for(; x < A.length() && y < B.length(); x++, y++) {
                        if (A.charAt(x) != B.charAt(y)) {
                            break;
                        } else if (A.charAt(x) > a) {
                            sb.append(A.substring(i, x)).append(B.substring(j, y));
                            i = x; j = y;
                            a = A.charAt(x);
                        }
                    }

                    if (x == A.length()) {
                        sb.append(B.charAt(j));
                        j++;
                    } else if (y == B.length()) {
                        sb.append(A.charAt(i));
                        i++;
                    } else {
                        if (A.charAt(x) < B.charAt(y)) {
                            sb.append(A.charAt(i));
                            i++;
                        } else {
                            sb.append(B.charAt(j));
                            j++;
                        }
                    }
                }
            }

            sb.append(A.substring(i)).append(B.substring(j));

            System.out.println(sb);
        }
    }
}

/*
More test cases:
Input:
1
DADC
DADB

Output:
DADADBDC


Input:
1
DADB
DADBC

output:
DADADBCDB

 */