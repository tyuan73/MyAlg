package AmazonTest2015;

/**
 * Created by yuantian on 5/15/15.
 */

/*

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class A1 {
/*
 * Complete the function below.
 */

    static String[] graph(String[] input) {
        if (input.length == 0) return input;

        HashMap<String, Boolean> map = new HashMap<>();
        HashMap<String, ArrayList<String>> g = new HashMap<>();
        for (String line : input) {
            if (line.indexOf(":") > 0) {
                String x = line.substring(0, line.indexOf(":"));
                map.put(x, false);
                String[] others = line.substring(line.indexOf(":") + 1).split(",");
                ArrayList<String> friends = new ArrayList<>();

                for (String y : others) {
                    if (y.length() > 0) {
                        friends.add(y);
                        map.put(y, false);
                    }
                }
                g.put(x, friends);
            }
        }

        String root = input[input.length - 1];
        map.put(root, true);
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(root);
        while (list.size() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            for (String x : list) {
                if (!g.containsKey(x)) continue;
                for (String y : g.get(x)) {
                    if (!map.get(y)) {
                        map.put(y, true);
                        temp.add(y);
                    }
                }
            }
            if (temp.size() > 0)
                ret.add(temp);
            list = temp;
        }

        String[] ans = new String[ret.size()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ret.size(); i++) {
            sb.setLength(0);
            for (String x : ret.get(i)) {
                sb.append(',').append(x);
            }
            if (sb.length() > 1)
                ans[i] = sb.substring(1);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = "/tmp/output";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;

        int _input_size = Integer.parseInt(in.nextLine());
        String[] _input = new String[_input_size];
        String _input_item;
        for (int _input_i = 0; _input_i < _input_size; _input_i++) {
            try {
                _input_item = in.nextLine();
            } catch (Exception e) {
                _input_item = null;
            }
            _input[_input_i] = _input_item;
        }

        res = graph(_input);
        for (int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}