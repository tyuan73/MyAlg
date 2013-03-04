/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class CombinationSum {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        Arrays.sort(candidates);
        combination(target, 0, candidates, 0, new ArrayList<Integer>(), ret);
        return ret;
    }

    void combination(int target, int sum, int[] c, int index, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if(sum > target)
            return;
        if(sum == target) {
            ret.add((ArrayList<Integer>)list.clone());
            return;
        }
        if(index == c.length)
            return;

        for(int i = index; i < c.length; i++) {
            list.add(c[i]);
            combination(target, sum + c[i], c, i, list, ret);
            list.remove(list.size()-1);
        }
    }
}
