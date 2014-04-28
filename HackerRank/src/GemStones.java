/**
 * Created by yuantian on 4/28/14.
 */

/**
 *
 John has discovered various rocks. Each rock has a composition consisting of various elements, where each element is represented by small latin letter (‘a’-‘z’). An element can be present multiple times in a rock. An element is called gem-element if it has at least one occurence in all of the rocks.

 Given the list of rocks with their compositions, you have to print how many different kinds of gems-elements he has.

 Input Format
 The first line consists of N, the number of rocks.
 Each of the next N lines contain rocks’ composition. Each composition consists of small alphabets of English language.

 Output Format
 Print the number of different kinds of gem-elements he has.

 Constraints
 1 ≤ N ≤ 100
 Each composition consists of only small latin letters (‘a’-‘z’).
 1 ≤ Length of each composition ≤ 100

 Sample Input

 3
 abcdde
 baccd
 eeabg
 Sample Output

 2
 Explanation
 Only “a”, “b” are the two kind of gem-elements, since these characters occur in each of the rocks’ composition.

 */

import java.util.*;

public class GemStones {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        int[] gem = new int[26];

        for(int i = 0; i < n; i++) {
            String line = in.nextLine();
            boolean[] exist = new boolean[26];
            for(int j = 0; j < line.length(); j++) {
                int index = (int) (line.charAt(j) - 'a');
                if (!exist[index]) {
                    gem[index]++;
                    exist[index] = true;
                }
            }
        }

        int total = 0;
        for (int i: gem) {
            if (i == n)
                total++;
        }
        System.out.println(total);
    }
}
