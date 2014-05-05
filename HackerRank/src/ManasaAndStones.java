/**
 * Created by yuantian on 5/5/14.
 */
/*
Manasa is out on a hike with friends. She finds a trail of stones with numbers on them. She starts following the trail and notices that two consecutive stones have a difference of either a or b. Legend has it that there is a treasure trove at the end of the trail and if Manasa can guess the value of the last stone, the treasure would be hers. Given that the number on the first stone was 0, find all the possible values for the number on the last stone.

Input Format
The first line contains an integer T i.e. the number of Test cases. T testcases follow.
Each testcase has 3 lines. The first line contains n ( the number of steps ) The second line contains a. The third line contains b.

Output Format
Space separated list of numbers which are the possible values of the last stone in increasing order.

Constraints
1 ≤ T ≤ 10
1 ≤ n, a, b ≤ 103

Sample Input 00

2
3
1
2
4
10
100
Sample Output 00

2 3 4
30 120 210 300
Explanation

All possible series for first test cases are given below.

0,1,2
0,1,3
0,2,3
0,2,4
Hence the answer 2 3 4.

Series with different number of final step for second test cases are

10, 20, 30
10, 20, 120
10, 110, 210
100, 200, 300
hence the answer 30 120 210 300
 */

import java.util.*;

public class ManasaAndStones {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();

            if (a == b) {
                System.out.println(a * (n-1));
                continue;
            }

            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }
            int total = a * (n-1);
            for (int i = 0; i < n; i++) {
                System.out.print(total + " ");
                total += (b-a);
            }
            System.out.println();
        }
    }
}
