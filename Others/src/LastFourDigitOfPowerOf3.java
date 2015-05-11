/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/10/15
 * Time: 10:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

/**
 * what is the last 4 digits of 3^2015 ?
 */

public class LastFourDigitOfPowerOf3 {
    public static void main(String[] args) {
        int base = 3;
        int result = 1;
        int pow = 2015;
        while (pow > 0) {
            if ((pow & 1) > 0) {
                result = (result * base) % 10000;
            }
            pow >>= 1;
            base = (base * base) % 10000;
        }
        System.out.println(result);
    }
}
