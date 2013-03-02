
/*
(()"	4	2

"(()(((()"
")))(((()()"

"(((((((()"	2	2	
 
"(()(((()"	4	2	
 
")))(((()()"	4	4	
 
"())()("


"((()()(()((()"	8	4
*/

public class LongestValidParentheses {
    public static void main(String[] args) {
        String s = "((()()(()((()";
        
        if(s == null || s.length() == 0)
            return;
        
        int max = 0;
        int cur = 0;
        int count = 0;
        int[] dp = new int[s.length()+1];
        for(int i = 1; i <= s.length(); i++) {
            char ch = s.charAt(i-1);
            if(ch == '(')
                count++;
            else {
                if(count > 0) {
                    count--;
                    cur += 2;
					//max = Math.max(max, cur-count);
                }
                if(count == 0) {
                    dp[i] = dp[i-cur] + cur;
                    max = Math.max(max, dp[i]);
                    //count = 0;
                    cur = 0;
                }
            }
        }
        
        max = Math.max(max, cur);
        //return max;
		System.out.println(max);
    }
}