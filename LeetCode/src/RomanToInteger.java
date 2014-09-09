/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:15 AM
 * To change this template use File | Settings | File Templates.
 */

/*

http://www.onlineconversion.com/roman_numerals_advanced.htm


A smaller number in front of a larger number means subtraction, all else means addition. For example, IV means 4, VI means 6.

You would not put more than one smaller number in front of a larger number to subtract. For example, IIV would not mean 3.

You must separate ones, tens, hundreds, and thousands as separate items. That means that 99 is XCIX, 90 + 9, but never should be written as IC. Similarly, 999 cannot be IM and 1999 cannot be MIM.

I	The numeral one. II is two, III is three. You seldom see IIII as 4, since IV can also mean 4, plus its shorter to write.
V	The numeral 5. IV is 4, VI is 6, VII is 7, VIII is 8.
X	The numeral 10. IX is 9, XI is 11, etc.
L	The numeral 50. XL would be 40.
C	The numeral 100. Think of Century having a hundred years. C is short for the Latin word Centum, but that's not very easy to remember.
D	The numeral 500.
M	The numeral 1000.
 */

public class RomanToInteger {
    public int romanToInt(String s) {
        int ret = 0, pre = 0;
        for(int i = 0; i < s.length(); i++) {
            int cur = trans(s.charAt(i));
            ret += cur;
            if (cur > pre) {
                ret -= 2 * pre;
            }
            pre = cur;
        }
        return ret;
    }

    private int trans(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
