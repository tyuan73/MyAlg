/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class GrayCode {
    public static void main(String[] args) {
        GrayCode gc = new GrayCode();
        ArrayList<Integer> ret = gc.grayCode(8);
        for(int i : ret) {
            System.out.println(Integer.toBinaryString(i));
        }
    }

    /**
     *  a straight forward solution
     */
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        ret.add(0);
        for(int i = 0; i < n; i++) {
            int x = 1 << i;
            for(int j = ret.size()-1; j >= 0; j--) {
                ret.add(ret.get(j)+x);
            }
        }

        return ret;
    }

    /**
     *  a better and fater solution.
     */
    public ArrayList<Integer> grayCode2(int n) {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        int size = 1<<n;
        for (int i = 0; i < size; ++i)
            ret.add((i >> 1) ^ i);

        return ret;
    }
}
