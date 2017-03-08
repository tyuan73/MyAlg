/**
 * Created by yuantian on 3/8/17.
 */
public class UTF8Validation393 {
    public boolean validUtf8(int[] data) {
        int i = 0;
        while (i < data.length) {
            int bytes = getBytes(data[i++]);
            if (bytes < 0) return false;
            while (--bytes > 0) {
                if (i == data.length || (data[i++] & 0xC0) != 0x80)
                    return false;
            }
        }
        return true;
    }

    private int getBytes(int x) {
        if ((x & 0x80) == 0) return 1;
        if ((x & 0xE0) == 0xC0) return 2;
        if ((x & 0xF0) == 0xe0) return 3;
        if ((x & 0xF8) == 0xf0) return 4;
        return -1;
    }
}
