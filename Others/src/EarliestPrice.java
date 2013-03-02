/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/24/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 *
 *
 *
 * Write a program to parse a text file.
 * The file is as follows:
 *      Company, time, price
 *      Microsoft, 9:00, 35
 *      Google, 10:00, 200
 *      Microsoft, 9:05, 35
 *      Google, 10:05, 200
 * ….
 *
 * Find the price for each company at the earliest time. So the result should
 * be:
 *      Microsoft, 35
 *      Google, 200
 *
 */

import java.util.*;

public class EarliestPrice {
    class Price {
        double time;
        int price;
        Price(double t, int p) {this.time = t; this.price = p;}
    }

    public static void main(String[] args) {
        String[] in = {
                "Microsoft, 9:00, 35",
                "Google, 10:00, 200",
                "Microsoft, 9:05, 35",
                "Google, 10:05, 200"};

        EarliestPrice e = new EarliestPrice();
        e.process(in);
    }

    void process(String[] input) {
        HashMap<String, Price> map = new HashMap<String, Price>();
        for(String s: input) {
            String[] slice = s.split(", ");
            double t = Double.parseDouble(slice[1].replace(':', '.'));
            int p = Integer.parseInt(slice[2]);
            if(map.containsKey(slice[0])) {
                Price x = map.get(slice[0]);
                if(t < x.time)
                    map.put(slice[0], new Price(t, p));
            } else
                map.put(slice[0], new Price(t, p));
        }

        for(String key : map.keySet())
            System.out.println(key + ", " + map.get(key).price);
    }
}
