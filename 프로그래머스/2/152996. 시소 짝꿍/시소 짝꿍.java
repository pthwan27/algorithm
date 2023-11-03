import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        Map<Double, Integer> wMap = new HashMap<>();
        
        for(int w : weights){
            double a = w * 1.0;
            double b = w * 1.0 / 2.0;
            double c = w * 2.0 / 3.0;
            double d = w * 3.0 / 4.0;
            
            if(wMap.containsKey(a)) answer += wMap.get(a);
            if(wMap.containsKey(b)) answer += wMap.get(b);
            if(wMap.containsKey(c)) answer += wMap.get(c);
            if(wMap.containsKey(d)) answer += wMap.get(d);
            
            wMap.put(w*1.0, wMap.getOrDefault(w*1.0, 0)+1);
        }
        return answer;
    }
}