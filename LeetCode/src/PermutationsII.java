/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class PermutationsII {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        Arrays.sort(num);
        ret.add(getList(num));

        int n = num.length;
        while (true) {
            int i = n - 1;
            while (i > 0 && num[i - 1] >= num[i])
                i--;
            if (i == 0)
                return ret;

            int j = n - 1;
            while (num[j] <= num[i - 1])
                j--;
            int x = num[i - 1];
            num[i - 1] = num[j];
            num[j] = x;

            for (int l = i, r = n - 1; l < r; l++, r--) {
                int y = num[l];
                num[l] = num[r];
                num[r] = y;
            }
            ret.add(getList(num));
        }
    }

    ArrayList<Integer> getList(int[] num) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : num)
            list.add(i);
        return list;
    }
}
