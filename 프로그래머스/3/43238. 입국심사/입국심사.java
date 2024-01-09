import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        
        Arrays.sort(times);
        
        long start = times[0];
        long end = (long)times[times.length-1] * n;
        
        long mid = 0;
        long sum = 0;
        while(start <= end){
            sum = 0;
            mid = (start + end) / 2;
            for(int time : times){
                sum += (mid / time);
            }
            
            if(sum >= n){
                end = mid - 1;
                answer = Math.min(answer, mid);
            }else{
                start = mid + 1;
            }
            
        }
        return answer;
    }
}