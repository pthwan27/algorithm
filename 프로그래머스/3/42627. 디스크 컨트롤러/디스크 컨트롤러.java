import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int endTime = 0; // 작업 끝난 시간
        int count = 0; // 작업이 처리된 갯수
        
        int jobIndex = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    
        int jobsLen = jobs.length;
        while(count < jobsLen){
            //endTime보다 같거나, 일찍 시작한 요청들 넣기
            while(jobIndex < jobsLen && endTime >= jobs[jobIndex][0]){
                pQ.offer(jobs[jobIndex++]);
            }
            
            if(pQ.isEmpty()){
                endTime = jobs[jobIndex][0];
            }else{
                int[] pollJob = pQ.poll();
                
                answer += endTime - pollJob[0] + pollJob[1];
                count++;
                endTime += pollJob[1];
            }
        }
        return answer / jobsLen;
    }
}