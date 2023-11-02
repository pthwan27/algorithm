import java.io.*;
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> tangerineMap = new HashMap<>();
       
        for(int i = 0; i < tangerine.length; i++){
            int tangerSize = tangerine[i];
            
            tangerineMap.put(tangerSize, tangerineMap.getOrDefault(tangerSize, 0) +1);
        }
        
        List<Integer> tangerineList = new ArrayList<>(tangerineMap.values());
        
        Collections.sort(tangerineList, Collections.reverseOrder());
        
        int i = 0;
        while(k > 0){
            k -= tangerineList.get(i);
            i++;
            answer++;
        }
        
        return answer;
    }
}