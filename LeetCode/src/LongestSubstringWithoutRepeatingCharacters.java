/**
 * Created with IntelliJ IDEA.
 * User: YuanTian
 * Date: 3/7/13
 * Time: 9:49 AM
 * To change this template use File | Settings | File Templates.
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[256];
        int last = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (index[ch] > last)
                last = index[ch];
            max = Math.max(max, i - last + 1);
            index[ch] = i + 1;
        }

        return max;
    }
}
