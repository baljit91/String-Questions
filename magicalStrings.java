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
