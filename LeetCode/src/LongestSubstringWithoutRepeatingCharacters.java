/**
 * Created with IntelliJ IDEA.
 * User: YuanTian
 * Date: 3/7/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        boolean[] set = new boolean[26];
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (set[index]) {
                int i1;
                do {
                    i1 = s.charAt(start++) - 'a';
                    set[i1] = false;
                } while (i1 != index);
            }
            set[index] = true;
            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}
