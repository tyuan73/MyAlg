/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class PalindromePartitioning {
    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        ArrayList<ArrayList<String>> ret = pp.partition("aabaa");
        for (ArrayList<String> list : ret) {
            for (String x : list)
                System.out.println(x);
            System.out.println();
        }
    }

    ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        helper(s, 0, new ArrayList<String>(), ret);
        return ret;
    }

    void helper(String s, int index, ArrayList<String> list, ArrayList<ArrayList<String>> ret) {
        if (index >= s.length()) {
            ret.add((ArrayList<String>) list.clone());
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                list.add(s.substring(index, i + 1));
                helper(s, i + 1, list, ret);
                list.remove(list.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int i, int j) {
        for (; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
}
