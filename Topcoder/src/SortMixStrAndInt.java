import java.util.*;

class SortMixStrAndInt {
    public static void main(String[] args) {
        /***********
         Assume input does not contain strings like "010".
         If that is the case, they will be treated as a number, and
         the leading 0s will be removed in output. For example,
         "010" will be translated to "10".
         ***********/
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] s = input.split(" ");

        int n = s.length;
        int[] index = new int[n];
        int l = 0, r = n - 1;
        for (int i = 0; i < n; i++) {
            if (isInteger(s[i]))
                index[l++] = i;
            else
                index[r--] = i;
        }
        Integer[] intArray = new Integer[l];
        String[] strArray = new String[n - l];
        for (int i = 0; i < l; i++)
            intArray[i] = Integer.parseInt(s[index[i]]);
        for (int i = l; i < n; i++)
            strArray[i - l] = s[index[i]];

        Arrays.sort(strArray);
        for (int i = 0, k = n - 1; i < strArray.length; i++, k--) {
            s[index[k]] = strArray[i];
        }

        Arrays.sort(intArray);
        for (int i = 0; i < intArray.length; i++) {
            s[index[i]] = Integer.toString(intArray[i]);
        }

        sc.close();

        StringBuffer sb = new StringBuffer();
        for (String x : s)
            sb.append(x).append(" ");
        sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}