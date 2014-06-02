/**
 * Created by yuantian on 6/2/14.
 */
/*
You are given a string of lowercase letters. Your task is to figure out the index of the character on whose removal will make the string a palindrome. There will always be a valid solution.

Input Format
The first line contains T i.e. number of test cases.
T lines follow, each line containing a string.

Output Format
Print the position ( 0 index ) of the letter by removing which the string turns into a palindrome. For string, such as

bcbc
we can remove b at index 0 or c at index 3. Both the answers are accepted.

Constraints
1 ≤ T ≤ 20
1 ≤ length of string ≤ 100005
All characters are latin lower case indexed.

Sample Input #00

3
aaab
baa
aaa
Sample Output #00

3
0
0
Explanation

In the given input, T = 3,

For input aaab, we can see that removing b from the string makes the string a palindrome, hence the position 3.
For input baa, removing b from the string makes the string palindrome, hence the position 0.
As the string aaa is already a palindrome, you can output 0, 1 or 2 as removal of any of the characters still maintains the palindrome property.
Possible doubt is answered here
 */
import java.util.*;
public class PalindromeIndex {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        in.nextLine();
        while(t-- > 0) {
            String line = in.nextLine();

            int l = 0, r = line.length()-1;
            for(; l < r; l++, r--) {
                if (line.charAt(l) != line.charAt(r)) {
                    break;
                }
            }

            if (l >= r) {
                System.out.println(l);
            } else {
                boolean valid = true;
                for(int x = l+1, y = r; x < y; x++, y--) {
                    if (line.charAt(x) != line.charAt(y)) {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    System.out.println(l);
                else
                    System.out.println(r);
            }
        }
    }
}
