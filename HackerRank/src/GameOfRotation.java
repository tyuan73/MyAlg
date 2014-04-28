/**
 * Created by yuantian on 4/28/14.
 */
/*
Mark is an undergraduate student and he is interested in rotation. A conveyor belt competition is going on in the town which Mark wants to win. In the competition, there’s conveyor belt which can be represented as a strip of 1xN blocks. Each block has a number written on it. The belt keeps rotating in such a way that after each rotation, each block is shifted to left of it and the first block goes to last position.

There is a switch near the conveyer belt which can stop the belt. Each participant would be given a single chance to stop the belt and his PMEAN would be calculated.

PMEAN is calculated using the sequence which is there on the belt when it stops. The participant having highest PMEAN is the winner. There can be multiple winners.

Mark wants to be among the winners. What PMEAN he should try to get which guarantees him to be the winner.

image1

where i is the index of a block at the conveyor belt when it is stopped. Indexing starts from 1.

Input Format
First line contains N denoting the number of elements on the belt.
Second line contains N space separated integers.

Output Format
Output the required PMEAN

Constraints
1 ≤ N ≤ 106
-109 ≤ each number ≤ 109

Sample Input

3
20 30 10
Sample Output

140
Explanation

Number on top can be written in these manners.
Initial numbers on belt, 20 30 10 PMEAN = 1x20 + 2x30 + 3x10 = 110
After first rotation, 30 10 20 PMEAN = 1x30 + 2x10 + 3x20 = 110
After second roation, 10 20 30 PMEAN = 1x10 + 2x20 + 3x30 = 140
So maximum possible value will be 140.
 */

import java.util.*;

public class GameOfRotation {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long[] data = new long[n];
        long t = 0;
        long total = 0;
        for (int i = 0; i < n; i++) {
            data[i] = in.nextLong();
            total += (i+1) * data[i];
            t += data[i];
        }

        long max = total;
        for (int i = 1; i < n; i++) {
            total = total - t + data[i-1] * n;
            max = Math.max(max, total);
        }
        System.out.println(max);
    }
}
