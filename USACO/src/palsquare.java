import java.io.*;

/**
 ID: tyuan731
 LANG: JAVA
 TASK: palsquare
 **/

public class palsquare {
    final static char[] map = {'0', '1','2', '3','4', '5','6', '7','8', '9','A', 'B','C', 'D','E', 'F','G', 'H','I', 'J'};
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int base = Integer.parseInt(f.readLine());
        for(int num = 1; num <= 300; num++) {
            int sqr = num* num;
            int[] table = new int[32];
            int index = 0;
            int x = sqr;
            while(x > 0) {
                table[index++] = x % base;
                x /= base;
            }
            boolean ok = true;
            for(int i = 0, j = index-1; i < j; i++, j--) {
                if(table[i] != table[j])
                {
                    ok = false;
                    break;
                }
            }
            if(ok)               {
                StringBuffer sb = new StringBuffer();
                for(int i = 0; i < index; i++)
                    sb.append(map[table[i]]);
                String output = sb.toString();
                sb.setLength(0);
                int z = num;
                index = 0;
                while(z > 0) {
                    table[index++] = z % base;
                    z /= base;
                }
                for(int i = index -1; i >= 0; i--)
                    sb.append(map[table[i]]);
                out.println(sb.toString() + " " + output);
            }
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }
}
