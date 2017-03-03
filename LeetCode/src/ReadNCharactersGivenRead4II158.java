/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/2/17
 * Time: 10:02 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ReadNCharactersGivenRead4II158 {

    char[] cache = new char[4];
    int from = 0, to = 0;

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        int m = 0;
        while (m < n) {
            if (from < to) {
                buf[m++] = cache[from++];
            } else {
                from = 0;
                to = read4(cache);
                if (to == 0)
                    break;
            }
        }
        return m;
    }

    /**
     * use large cache:
     * 
     * char[] cache = new char[2048];
     * int from = 0, to = 0;
     */

    char[] temp = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int readLongCache(char[] buf, int n) {
        int m = 0;
        while (m < n) {
            if (from < to) {
                buf[m++] = cache[from++];
            } else {
                from = 0;
                to = 0;
                int count = 4;
                while (to < 2048 && count == 4) {
                    count = read4(temp);
                    System.arraycopy(temp, 0, cache, to, count);
                    to += count;
                }
                if (to == 0)
                    break;
            }
        }
        return m;
    }

    /* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
    private int read4(char[] buf) {
        return 0;
    }
}
