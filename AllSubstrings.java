//all substring of given string


public class AllSubstrings {
	
	public static void logic(String s){
		int len = s.length();
		
		for(int i = 0; i < len; i++){
			for(int j = i+1; j <= len;j++){
				System.out.println(s.substring(i, j));
			}
		}
	}
	public static void main(String[] args){
		
		logic("abcd");
	}
}
