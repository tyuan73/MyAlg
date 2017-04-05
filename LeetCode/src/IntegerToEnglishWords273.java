/**
 * Created by yuantian on 4/4/17.
 */
public class IntegerToEnglishWords273 {
    final static private String[] map = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    final static private int[] MARK = {1000000000, 1000000, 1000, 100, 90, 80, 70, 60, 50, 40, 30, 20};
    final static private String[] STR = {"Billion", "Million", "Thousand", "Hundred", "Ninety", "Eighty", "Seventy", "Sixty", "Fifty", "Forty", "Thirty", "Twenty"};

    public String numberToWords(int num) {
        for (int i = 0; i < MARK.length; i++) {
            if (num >= MARK[i]) {
                StringBuilder sb = new StringBuilder();
                if (i <= 3)
                    sb.append(numberToWords(num / MARK[i])).append(" ");
                sb.append(STR[i]);
                if (num % MARK[i] != 0)
                    sb.append(" ").append(numberToWords(num % MARK[i]));
                return sb.toString();
            }
        }
        return map[num];
    }
}
