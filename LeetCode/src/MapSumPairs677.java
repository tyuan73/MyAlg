/*

*/

import java.util.*;
import java.io.*;

public class MapSumPairs677 {
    TreeMap<String, Integer> map = null;

    /**
     * Initialize your data structure here.
     */
    public MapSumPairs677() {
        map = new TreeMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        // use subMap() of TreeMap to grab all key-value pairs between
        // prefix and prefix + "}" - assume keys only contain letters.
        return map.subMap(prefix, prefix + "{").values().stream().mapToInt(Integer::intValue).sum();
    }
}