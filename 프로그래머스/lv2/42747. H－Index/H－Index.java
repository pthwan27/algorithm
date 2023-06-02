import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        // citations[i]에서 i값을 증가시키고 논문의 수를 감소시키면서 비교 했을 때 
        // 인용 횟수가 논문의 수 이상이 되었을 때의 논문의 수가 H-Index
        Arrays.sort(citations);
        for(int i =0; i < citations.length;i++)
        {            
            int highCitation = citations.length-i;
            if(citations[i] >= highCitation){                
                answer = highCitation;
                break;               
            }
        }       
        return answer;
    }
}