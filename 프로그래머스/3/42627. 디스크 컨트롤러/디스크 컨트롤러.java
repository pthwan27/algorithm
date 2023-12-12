import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int endTime = 0;
        int count = 0;
        
        Arrays.sort(jobs, (o1,o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> pQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int idx = 0;
        while(count < jobs.length){
            while(idx < jobs.length && endTime >= jobs[idx][0]){
                pQ.offer(jobs[idx++]);
            }
            
            if(pQ.isEmpty()){
                endTime = jobs[idx][0];
            }else{
                int[] pollTask = pQ.poll();
                answer += endTime - pollTask[0] + pollTask[1];
                count++;
                endTime += pollTask[1];
            }
        }
        return answer / jobs.length;
    }
}