import java.util.*;

class MissingCharacters {
	static public void main(String[] args) {
		String s ="dkchaDSADHHRBEOPpuwjcpiapj39ruijdikoko";
		
		HashSet<Character> set = new HashSet<Character>();
		for(char i = 'a'; i <= 'z'; i++) {
			set.add(i);
		}
		
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(ch >= 'A' && ch <= 'Z')
				ch -= 'A' - 'a';
			set.remove(ch);
		}
		
		for(char c : set)
			System.out.println(c);
	}
}