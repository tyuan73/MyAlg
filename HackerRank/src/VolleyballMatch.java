/**
 * Created by yuantian on 4/23/14.
 */

/*
Volleyball Match

Tatyana is a big sports fan and she likes volleyball a lot! She writes down the final scores of the game after it has ended in her notebook.

If you are not familiar with the rules of volleyball, here’s a brief:

2 teams play in total
During the course of the game, each team gets points, and thus increases its score by 1.
The initial score is 0 for both teams.
The game ends when

One of the teams gets 25 points and another team has < 24 points ( strictly less than 24).
If the score ties at 24:24, the teams continue to play until the absolute difference between the scores is 2.
Given the final score of a game in the format A:B i.e., the first team has scored A points and the second has scored B points, can you print the number of different sequences of getting points by teams that leads to this final score?

Input Format
The first line contains A and the second line contains B.

Constraints

0 ≤ A , B ≤ 109

Output Format
Output the number of different sequences of getting points by the teams that leads to the final score A : B. Final means that the game should be over after this score is reached. If the number is larger than 109+7, output number modulo 109 + 7. Print 0 if no such volleyball game ends with the given score.

Example input #00

3
25
Example output #00

2925
Example input #01

24
17
Example output #01

0
Explanation #01

There’s no game of volleyball that ends with a score of 24 : 17.
 */

import java.util.*;

/*
* replace 2-dimension array with 1-dimension array
* A little improve over previous version
* */
public class VolleyballMatch {
    static long P = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long a = in.nextLong();
        long b = in.nextLong();

        if (a < b) {
            long x = a;
            a = b;
            b = x;
        }

        if (a - b <= 1 || a < 25 || (a > 25 && a-b != 2)) {
            System.out.println(0);
        } else {
            long[] dp = new long[25];
            Arrays.fill(dp, 1);

            for (int i = 1; i < 25; i++) {
                for (int j = 1; j < 25; j++)
                    dp[j] = (dp[j-1] + dp[j]) % P;
            }

            if (a == 25) {
                System.out.println(dp[(int)b]);
            } else {
                b -= 24;
                long out = dp[24];
                //System.out.println(out);
                long base = 2;
                while (b > 0) {
                    if ((b & 1) > 0)
                        out = (out * base) % P;
                    b >>= 1;
                    base = (base * base) % P;
                }
                System.out.println(out);
            }
        }
    }
}

/**
 * Test1
 *  input:
 *      1000000000
 *      999999998
 *  output:
 *      575548948
 */
