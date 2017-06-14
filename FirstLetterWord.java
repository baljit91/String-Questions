//Note : dont use split(" ") in this ques ...coz we might not have single spaces between words
public class FirstLetterWord {
	
	public static String logic(String s){
		
		StringBuilder sb = new StringBuilder();
        int len = s.length();
        
        if(len == 0)
        	return "";
        
        //just iterate throug the string
        for(int i=0; i < len ;i++){
        	sb.append(s.charAt(i));
        	i++;
        	while(i < len  && s.charAt(i) != ' '){
        		i++;
        	}
        	while(i < len  && s.charAt(i) == ' '){
        		i++;
        	}
        	i--;
        }
        
        return sb.toString();
		
		//System.out.println(str.toString());
	}
	
	public static void main(String[] args){
		
		String result  = logic("geeks for geeks");
		System.out.print(result);
	}
}
