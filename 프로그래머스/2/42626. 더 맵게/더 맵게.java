import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> scovQue = new PriorityQueue<>((s1, s2) -> s1 - s2);
        
        for(int i = 0; i < scoville.length; i++){
            scovQue.offer(scoville[i]);
        }
        
        if(scovQue.peek() > K){
            return answer;
        }
        while(scovQue.peek() < K){
            if(scovQue.size() == 1){
                return scovQue.peek() >= K ? answer : -1; 
            }
            
            int newScov = scovQue.poll() + (scovQue.poll() * 2); 
            scovQue.offer(newScov);
            answer++;

            if(newScov == 0){
                return -1;
            }
        }
        
        return answer;
    }
}