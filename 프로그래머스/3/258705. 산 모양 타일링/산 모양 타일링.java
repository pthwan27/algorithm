class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int dp[][] = new int[n+1][2];
        
        dp[0][0] = 1;
        int mod = 10007;
        
        for(int i = 1; i < n+1; i++){
            if(tops[i-1] == 1){
                dp[i][0] = (dp[i-1][0] * 3 + dp[i-1][1] * 2) % mod;
            }else{
                dp[i][0] = (dp[i-1][0] * 2 + dp[i-1][1]) % mod;
            }
            
            dp[i][1] = (dp[i-1][1] + dp[i-1][0]) % mod;
        }
        
        return (dp[n][0] + dp[n][1]) % mod;
    }
}