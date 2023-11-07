import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][];
        for(int i = 0; i < dp.length; i++){
            dp[i] = new int[i+1];
            
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[0][0] = triangle[0][0];
        dp[1][0] = triangle[0][0] + triangle[1][0];
        dp[1][1] = triangle[0][0] + triangle[1][1];
        
        for(int i = 2; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                if(j == 0){
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }else if(j == dp[i].length - 1){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
                
                if(i == dp.length-1){
                    answer = Math.max(dp[i][j], answer);
                }
            }
            
           
        }
        return answer;
    }
}