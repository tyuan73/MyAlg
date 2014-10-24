package poj1001;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int num = in.nextInt();
        ArrayList<Double> ret = new ArrayList<Double>();
        //for(int i = 0; i < num; i++) {
        while (in.hasNext()) {
            double n = in.nextDouble();
            int k = in.nextInt();
            //System.out.println(cal(n, k));
            ret.add(cal(n, k));
        }

        for (double x : ret) {
            String s = Double.toString(x);
            if (s.charAt(0) == '0')
                System.out.println(s.substring(1));
            else
                System.out.println(x);


        }
        //System.out.println(x);
    }

    static double cal(double d, int k) {
        double ret = 1.0;
        while (k > 0) {
            if ((k & 1) > 0)
                ret *= d;
            d *= d;
            k = k >> 1;
        }
        return ret;
    }
}