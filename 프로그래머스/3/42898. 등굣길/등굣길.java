class Solution {
    static int div = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m+1][n+1];
        dp[1][1] = 1;
        
        int[][] map = new int[m+1][n+1];
        for(int i = 0; i < puddles.length; i++){
            map[puddles[i][0]][puddles[i][1]] = -1;
        }
        
        for(int r = 1; r <= m; r++){
            for(int c = 1; c <= n; c++){
                if(map[r][c] == -1){
                    continue;                    
                }else{
                    dp[r][c] = Math.max(dp[r][c], (dp[r-1][c] + dp[r][c-1]) % div);                    
                }
                
            }
        }
        
        int answer = dp[m][n];
        return answer;
    }
}