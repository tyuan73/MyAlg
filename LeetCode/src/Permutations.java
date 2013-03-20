/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class Permutations {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        helper(num, 0, ret);
        return ret;
    }

    void helper(int[] num, int index, ArrayList<ArrayList<Integer>> ret) {
        if (index == num.length) {
            ArrayList<Integer> list = getList(num);
            ret.add(list);
            return;
        }

        for (int i = index; i < num.length; i++) {
            int x = num[i];
            num[i] = num[index];
            num[index] = x;
            helper(num, index + 1, ret);
            num[index] = num[i];
            num[i] = x;
        }
    }

    ArrayList<Integer> getList(int[] num) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : num)
            list.add(i);
        return list;
    }
}
