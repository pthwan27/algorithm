import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];
        
        int num = 0;
        
        int answer = -1; 
        
        for(int i = 1; i < 9; i++){
            num = (num * 10) + N;
            dp[i] = new HashSet<>();
            dp[i].add(num); 
        }
        
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < i; j++){                
                for(Integer curNum : dp[j]){
                    for(Integer prevNum : dp[i - j]){
                        dp[i].add(curNum + prevNum);
                        dp[i].add(curNum - prevNum);
                        dp[i].add(curNum * prevNum);
                        if(prevNum != 0){
                            dp[i].add(curNum / prevNum);
                        }
                        
                        if(curNum != 0){
                            dp[i].add(prevNum / curNum);
                        }
                    }
                }
            }
            
            if(dp[i].contains(number)){
                answer = i;
                return answer;
            }
        }
        
        return -1;
    }
}