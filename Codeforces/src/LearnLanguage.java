/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LearnLanguage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] group = new int[m];
        int ret = 0;
        int id = 1;
        while(n-- > 0) {
            int k = in.nextInt();
            if(k == 0) {
                ret++;
                continue;
            }
            int thisid = id++;
            while(k-- > 0) {
                int lang = in.nextInt()-1;
                if(group[lang] == 0)
                    group[lang] = thisid;
                else {
                    int newid  = group[lang];
                    for(int j = 0; j < m; j++) {
                        if(group[j] == thisid)
                            group[j] = newid;
                    }
                    thisid = newid;
                }
            }
        }

        HashSet<Integer> set = new HashSet<Integer>();
        for(int i : group)
            if(i != 0)
                set.add(i);
        if(set.size() > 0)
            ret += set.size()-1;
        System.out.println(ret);
    }
}
