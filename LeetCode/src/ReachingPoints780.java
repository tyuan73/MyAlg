import java.util.*;
import java.io.*;

public class ReachingPoints780 {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        int x1 = sx % sy, y1 = sy % sx;
        while (tx >= sx && ty >= sy) {
            int x2 = tx % ty, y2 = ty % tx;
            if (tx == sx)
                return y2 == y1;
            if (ty == sy)
                return x2 == x1;
            tx = x2;
            ty = y2;
        }
        return false;
    }
}
