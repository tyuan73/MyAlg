/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/6/17
 * Time: 10:20 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class NthDigit400 {
    public int findNthDigit(int n) {
        long a = 1, count = 9, start = 1;
        while (n > a * count) {
            n -= a * count;
            a++;
            count *= 10;
            start *= 10;
        }
        n--;
        long num = start + n / a;
        String s = Long.toString(num);
        return s.charAt((int) (n % a)) - '0';
    }
}
