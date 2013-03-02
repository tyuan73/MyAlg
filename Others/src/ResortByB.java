import java.util.*;

class ResortByB {
	public static void main(String[] args) {
		String A = "catch";
		String B = "tar";
		
		Map<Character, Integer> m = new LinkedHashMap<Character, Integer>();
		for(int i = 0; i < A.length(); i++) {
			char ch = A.charAt(i);
			if(m.containsKey(ch)) {
				int c = m.get(ch);
				m.put(ch, c+1);
			} else {
				m.put(ch, 1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < B.length(); i++) {
			char ch = B.charAt(i);
			if(!m.containsKey(ch)) continue;
			
			int c = m.remove(ch);
			for(int j = 0; j < c; j++)
				sb.append(ch);
		}
		
		for(char ch : m.keySet()) {
			int c = m.get(ch);
			for(int i = 0; i < c; i++) {
				sb.append(ch);
			}
		}
		
		System.out.println("" + sb.toString());
	}
}