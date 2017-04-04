/**
 * Created by yuantian on 3/21/17.
 */

import java.util.*;

public class NestedListWeightSum_II_364 {
    /**
     * the first try. find depth first and then sum up.
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int dep = getDep(nestedList);
        return sum(nestedList, dep);
    }

    private int getDep(List<NestedInteger> list) {
        if (list == null) return 0;
        int dep = 0;
        for (NestedInteger ni : list) {
            if (!ni.isInteger())
                dep = Math.max(dep, getDep(ni.getList()));
        }
        return dep + 1;
    }

    private int sum(List<NestedInteger> list, int dep) {
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * dep;
            } else {
                sum += sum(ni.getList(), dep - 1);
            }
        }
        return sum;
    }

    /**
     * a much better solution
     */
    public int depthSumInverse1(List<NestedInteger> nestedList) {
        int sum = 0, unweighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> next = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    next.addAll(ni.getList());
            }
            sum += unweighted;
            nestedList = next;
        }
        return sum;
    }

    /**
     * definition of NestedInteger
     */
    class NestedInteger {
        // Constructor initializes an empty nested list.
        NestedInteger() {
        }

        ;

        // Constructor initializes a single integer.
        NestedInteger(int value) {
        }

        ;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return true;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 1;
        }

        ;

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        ;

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        ;

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }

        ;
    }
}
