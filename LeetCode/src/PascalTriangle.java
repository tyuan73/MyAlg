/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class PascalTriangle {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i)
                    cur.add(1);
                else
                    cur.add(list.get(j-1) + list.get(j));
            }
            ret.add(cur);
            list = cur;
        }
        return ret;
    }
}
