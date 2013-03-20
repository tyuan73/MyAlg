/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class PascalTriangleII {
    public ArrayList<Integer> getRow(int rowIndex) {
        int[] tri = new int[rowIndex + 1];
        tri[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--)
                tri[j] = tri[j] + tri[j - 1];
        }

        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i : tri)
            ret.add(i);
        return ret;
    }
}
