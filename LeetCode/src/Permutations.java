/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class Permutations {
    List<List<Integer>> ret = null;

    public List<List<Integer>> permute(int[] num) {
        ret = new ArrayList<List<Integer>>();
        internalPerm(num, 0);
        return ret;
    }

    private void internalPerm(int[] num, int index) {
        if (index == num.length) {
            addToRet(num);
            return;
        }

        for (int i = index; i < num.length; i++) {
            int n = num[index];
            num[index] = num[i];
            num[i] = n;
            internalPerm(num, index + 1);
            num[i] = num[index];
            num[index] = n;
        }
    }

    private void addToRet(int[] num) {
        List<Integer> list = new ArrayList<Integer>();
        for (int n : num)
            list.add(n);
        ret.add(list);
    }
}
