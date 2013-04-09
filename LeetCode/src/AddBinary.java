public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return b;

        int n = a.length(), m = b.length();
        char[] out = new char[Math.max(n, m) + 1];
        int i1 = n - 1, i2 = m - 1, i3 = out.length - 1;
        int a1 = 0, a2 = 0, carry = 0;
        for (; i1 >= 0 || i2 >= 0; i1--, i2--) {
            a1 = i1 >= 0 ? a.charAt(i1) - '0' : 0;
            a2 = i2 >= 0 ? b.charAt(i2) - '0' : 0;
            int sum = a1 + a2 + carry;
            if (sum > 1)
                carry = 1;
            else carry = 0;

            sum = sum % 2;
            out[i3--] = (char) (sum + '0');
        }

        if (carry > 0)
            out[i3] = '1';
        else
            out[i3] = '0';

        String ret = new String(out);

        if (ret.charAt(0) == '0')
            ret = ret.substring(1);
        return ret;
    }
}