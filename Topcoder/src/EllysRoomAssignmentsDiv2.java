import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class EllysRoomAssignmentsDiv2 {
    public double getProbability(String[] ratings) {
        StringBuilder sb = new StringBuilder();
        for (String s : ratings)
            sb.append(s);
        String[] reg = sb.toString().split(" ");
        int n = reg.length;
        int el = Integer.parseInt(reg[0]);
        int rank = 0;
        for (int i = 1; i < n; i++) {
            int x = Integer.parseInt(reg[i]);
            if (x > el)
                rank++;
        }
        if (rank == 0)
            return 1.0;

        int r = (n + 19) / 20;
        if (rank < r)
            return 0.0;
        return 1.0 / (double) r;
    }
}
//Powered by [KawigiEdit] 2.0!
