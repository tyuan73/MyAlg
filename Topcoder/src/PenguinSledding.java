import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

//SRM 566 div1 250 pints
public class PenguinSledding {
    public long countDesigns(int numCheckpoints, int[] checkpoint1, int[] checkpoint2) {
        int[] deg = new int[numCheckpoints + 1];
        boolean[][] map = new boolean[numCheckpoints + 1][numCheckpoints + 1];
        for (int i = 0; i < checkpoint1.length; i++) {
            deg[checkpoint1[i]]++;
            deg[checkpoint2[i]]++;
            map[checkpoint1[i]][checkpoint2[i]] = true;
            map[checkpoint2[i]][checkpoint1[i]] = true;
        }

        //long total = 1 + checkpoint1.length;
        long total = 0;
        for (int i = 1; i <= numCheckpoints; i++) {
            //total += (1l << deg[i]) - 1 - deg[i];
            total += 1l << deg[i];
        }
        total -= numCheckpoints - 1 + checkpoint1.length;

        for (int i = 1; i <= numCheckpoints; i++) {
            if (deg[i] < 2) continue;
            for (int j = i + 1; j <= numCheckpoints; j++) {
                if (deg[j] < 2) continue;
                for (int k = j + 1; k <= numCheckpoints; k++) {
                    if (map[i][j] && map[i][k] && map[k][j])
                        total++;
                }
            }
        }

        return total;
    }

    //<%:tesing-code%>
}
//Powered by [KawigiEdit] 2.0!
