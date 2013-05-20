import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class SmartWordToy
{
	public int minPresses(String start, String finish, String[] forbid)
	{
		if(start.equals(finish))
			return 0;
			
		boolean[][][][] invalid = new boolean[26][26][26][26];
		for(String str : forbid) {
			String[] s = str.split(" ");
			for(int i = 0; i < s[0].length(); i++) {
				for(int j = 0; j < s[1].length(); j++) {
					for(int k = 0; k < s[2].length(); k++) {
						for(int m =0; m < s[3].length(); m++) 
							invalid[s[0].charAt(i) - 'a'][s[1].charAt(j)-'a'][s[2].charAt(k)-'a'][s[3].charAt(m)-'a'] = true;
					}
				}
			}
		}
		if(invalid[finish.charAt(0)-'a'][finish.charAt(1)-'a'][finish.charAt(2)-'a'][finish.charAt(3)-'a'])
			return -1;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put(start, 0);
		LinkedList<String> deque = new LinkedList<String>();
		deque.addLast(start);
		while(deque.size() > 0) {
			String s = deque.poll();
			int steps = map.get(s);
			char[] s1 = s.toCharArray();
			for(int i = 0; i < s1.length; i++) {
				char orig = s1[i];
				s1[i] = (char) (orig - 1);
				if(s1[i] < 'a') s1[i] = 'z';
				if(!invalid[s1[0]-'a'][s1[1]-'a'][s1[2]-'a'][s1[3]-'a']) {
					String s2 = new String(s1);
					if(s2.equals(finish))
						return steps+1;
					
					if(!map.containsKey(s2)) {
						map.put(s2, steps+1);
						deque.addLast(s2);
					}
				}
				s1[i] = (char) (orig +1);
				if(s1[i] > 'z') s1[i] = 'a';
				if(!invalid[s1[0]-'a'][s1[1]-'a'][s1[2]-'a'][s1[3]-'a']) {
					String s2 = new String(s1);
					if(s2.equals(finish))
						return steps+1;
					if(!map.containsKey(s2)) {
						map.put(s2, steps+1);
						deque.addLast(s2);
					}
				}
				s1[i] = orig;
			}
		}
		return -1;
	}
	
	
}
//Powered by [KawigiEdit] 2.0!
