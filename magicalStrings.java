/**
Julia calls a string magical if the following conditions are satisfied:
Each letter ∈ {a, e, i, o, u}.
"a" must only be followed by "e".
"e" must only be followed by "a" or "i".
"i" must only be followed by "a", "e", "o", or "u".
"o" must only be followed by "i" or "u".
"u" must only be followed by "a".
 
Complete the magicalStrings function in your editor. It has 1 parameter: an integer, n, denoting the desired length of a magical string. It must return an integer denoting the number of magical strings of length n, modulo (109 + 7).
 
Input Format
The locked stub code in your editor reads the following input from stdin and passes it to your function:
The first line contains an integer, n, denoting the desired length of a magical string.
 
Constraints
0 < n < 105
 
Output Format
Your function must return an integer denoting the number of magical strings of length n, modulo (109 + 7). This is printed to stdout by the locked stub code in your editor.
 
Sample Input 0
1
 
Sample Output 0
5
 
Sample Input 1
2
 
Sample Output 1
10
 
Sample Input 2
3
 
Sample Output 2
19
 
Explanation
Sample Case 0:
There 5 magical strings: {"a", "e", "i", "o", "u"}.
 
Sample Case 1:
There 10 magical strings: {"ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou", "ua"}.
 
Sample Case 2:
There 19 magical strings: {"aea", "aei", "eae", "eia", "eie", "eio", "eiu", "iae", "iea", "iei", "ioi", "iou", "iua", "oia", "oie", "oio", "oiu", "oua", "uae"}.
 -}

-- "a" must only be followed by "e".
-- "e" must only be followed by "a" or "i".
-- "i" must only be followed by "a", "e", "o", or "u".
-- "o" must only be followed by "i" or "u".
-- "u" must only be followed by "a".
**/







class Main {
    // with queue
    static int magicalStrings(int n) {
        Queue<String> queue = new LinkedList();
        HashMap<Character,ArrayList<String>> map = new HashMap<Character,ArrayList<String>>();
        map.put('a',new ArrayList(Arrays.asList("e")));
        map.put('e',new ArrayList(Arrays.asList("a","i")));
        map.put('i',new ArrayList(Arrays.asList("a","e","o","u")));
        map.put('o',new ArrayList(Arrays.asList("i","u")));
        map.put('u',new ArrayList(Arrays.asList("a")));
   
        queue.add("a");
        queue.add("e");
        queue.add("i");
        queue.add("o");
        queue.add("u");
        int queueSize = 0;
        
        
        for(int i =1; i< n; i++){
            queueSize = queue.size();
            int j = 0;
            while(j < queueSize){
                String current = queue.poll();
                char currentVowel = current.charAt(current.length()-1);
                ArrayList<String> list = map.get(currentVowel);
                for(int k=0; k< list.size();k++){
                    String res = current + list.get(k);
                    queue.add(res);
                }
                j++;
            }  
        }
        
            
        return queue.size();
    }


   //with hashmap
    static int magicalStrings(int n) {
        Queue<String> queue = new LinkedList();
        HashMap<Character,ArrayList<Character>> map = new HashMap<Character,ArrayList<Character>>();
        map.put('a',new ArrayList(Arrays.asList('e')));
        map.put('e',new ArrayList(Arrays.asList('a','i')));
        map.put('i',new ArrayList(Arrays.asList('a','e','o','u')));
        map.put('o',new ArrayList(Arrays.asList('i','u')));
        map.put('u',new ArrayList(Arrays.asList('a')));
        
        //a 3  6      e+i+u
            
        //e 2  5      a + i
        //i 2  3      e + o
        //o 1  2      i
        //u 2  3      i + o
        
        Map<Character,Integer> combmap = new LinkedHashMap<Character,Integer>();
        
        for(Character key : map.keySet()){
            combmap.put(key,0);
        }
        
        int[] match = new int[5];
        Arrays.fill(match,1);

        for(int i =1; i< n; i++){
            int x = 0;
            for(Character key : map.keySet()){
                int curr = match[x];
                ArrayList<Character> list = map.get(key);
                for(int k=0; k< list.size();k++){
                    combmap.put(list.get(k), combmap.get(list.get(k)) + curr);
                }
                x++;
            }
            x = 0;
            for(Character key : map.keySet()){
                //counter.put(key,combmap.get(key));
                match[x] = combmap.get(key);
                x++;
            }
            
            for(Character key : map.keySet()){
                combmap.put(key,0);
            }
        }
        //System.out.println(queue.peek());
        
        
        int result = 0;
        
        for(int i=0; i<match.length; i++)
            result += match[i];
        
     
        return result;
    }
    
    
    //with formula dp
    static int magicalStrings(int n) {
        int[] dummy = new int[5];
        Arrays.fill(dummy,1);
        
        int[] arr = new int[5];
        
        for(int i=1; i <n; i++){
            arr[0] = dummy[1] + dummy[2] + dummy[4];
            arr[1] = dummy[0] + dummy[2];
            arr[2] = dummy[1] + dummy[3];
            arr[3] = dummy[2];
            arr[4] = dummy[2] + dummy[3];
            
            for(int j=0; j<arr.length;j++){
                dummy[j] = arr[j];
            }
            
            //Arrays.fill(arr,0);
        }
        
        //a 3  6      e+i+u
            
        //e 2  5      a + i
        //i 2  3      e + o
        //o 1  2      i
        //u 2  3      i + o
        
        int result = 0;
        
        for(int i=0; i<dummy.length; i++)
            result += dummy[i];
        
        return result;
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(magicalStrings(7));
        
    }
}
