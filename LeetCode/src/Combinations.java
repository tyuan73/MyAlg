/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class Combinations {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        helper(n, k, 1, new ArrayList<Integer>(), ret);
        return ret;
    }

    void helper(int n, int k, int index, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if (list.size() == k) {
            ret.add((ArrayList<Integer>) list.clone());
            return;
        }

        for (int i = index; i <= n; i++) {
            list.add(i);
            helper(n, k, i + 1, list, ret);
            list.remove(list.size() - 1);
        }
    }
}
